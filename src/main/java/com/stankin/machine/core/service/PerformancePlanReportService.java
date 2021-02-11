package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.*;
import com.stankin.machine.core.dto.DateFilterDTO;
import com.stankin.machine.core.dto.report.ReportExecutePlanEmpDTO;
import com.stankin.machine.core.dto.report.ReportImplementDetailDTO;
import com.stankin.machine.core.dto.report.ReportTechOperationTypeDTO;
import com.stankin.machine.core.repository.ReportPlanByTechOperationTypeRepository;
import com.stankin.machine.core.service.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PerformancePlanReportService {

    private final ExecutorProgramService executorProgramService;
    private final EmployeeService employeeService;
    private final TechOperationService techOperationService;
    private final ReportPlanByTechOperationTypeRepository reportPlanByTechOperationTypeRepository;
    private final PlanService planService;
    private final TechProcessService techProcessService;

    public PerformancePlanReportService(ExecutorProgramService executorProgramService,
                                        EmployeeService employeeService,
                                        TechOperationService techOperationService,
                                        ReportPlanByTechOperationTypeRepository reportPlanByTechOperationTypeRepository,
                                        PlanService planService,
                                        TechProcessService techProcessService) {
        this.executorProgramService = executorProgramService;
        this.employeeService = employeeService;
        this.techOperationService = techOperationService;
        this.reportPlanByTechOperationTypeRepository = reportPlanByTechOperationTypeRepository;
        this.planService = planService;
        this.techProcessService = techProcessService;
    }

    public List<ReportExecutePlanEmpDTO> reportExecutePlanByEmp(@NotNull DateFilterDTO dateFilterDTO) {
        log.trace(">>reportExecutePlanByEmp... dateFilterDTO={}", dateFilterDTO);
        List<ReportExecutePlanEmpDTO> reportExecutePlanEmpDTOList = new ArrayList<>();
        List<Employee> employeeList = employeeService.findAllByStartAndEndDate(dateFilterDTO.getStartDate(), dateFilterDTO.getEndDate());
        for (Employee employee : employeeList) {
            Double executePlanFact = executorProgramService.calculateActualMachineTime(employee.getId(), null,
                    dateFilterDTO.getStartDate(), dateFilterDTO.getEndDate());
            String empFullName = employeeService.formatterFullName(employee.getFirstName(),
                    employee.getLastName(), employee.getMiddleName());
            List<ExecutorProgram> executorProgramList = executorProgramService
                    .findAllByEmpAndStartAndEndDate(employee.getId(), dateFilterDTO.getStartDate(), dateFilterDTO.getEndDate());
            double sumScheduledMachineTime = 0.0001d;

            for (ExecutorProgram executorProgram : executorProgramList) {
                Optional<TechOperation> techOperationOptional = techOperationService.findByFileName(executorProgram.getFileName());
                if (techOperationOptional.isPresent()) {
                    TechOperation techOperation = techOperationOptional.get();
                    Double scheduledMachineTime = techOperation.getScheduledMachineTime();
                    if (scheduledMachineTime != null) sumScheduledMachineTime += scheduledMachineTime;
                }
            }
            if (executePlanFact == null) {
                executePlanFact = 0.00d;
            }
            ReportExecutePlanEmpDTO reportExecutePlanEmpDTO = new ReportExecutePlanEmpDTO(
                    empFullName, sumScheduledMachineTime, executePlanFact,
                    executePlanFact / sumScheduledMachineTime);
            reportExecutePlanEmpDTOList.add(reportExecutePlanEmpDTO);
        }
        return reportExecutePlanEmpDTOList;
    }

    public List<ReportTechOperationTypeDTO> reportPlanByTechOperationType(DateFilterDTO dateFilterDTO) {
        log.trace(">>reportPlanByTechOperationType... dateFilterDTO={}", dateFilterDTO);
        List<ReportTechOperationTypeDTO> reportTechOperationTypeDTOList = reportPlanByTechOperationTypeRepository
                .reportPlanByTechOperation(dateFilterDTO.getStartDate(), dateFilterDTO.getEndDate());
        List<ReportTechOperationTypeDTO> resultReportTechOperationTypeList = new ArrayList<>();
        for (ReportTechOperationTypeDTO reportTechOperationTypeDTO :
                reportTechOperationTypeDTOList.stream()
                        .filter(distinctByKey(ReportTechOperationTypeDTO::getTechOperationType))
                        .collect(Collectors.toList())) {
            ReportTechOperationTypeDTO tempReport = new ReportTechOperationTypeDTO();
            tempReport.setTechOperationType(reportTechOperationTypeDTO.getTechOperationType());
            Long factAmount = 0L;
           for(ReportTechOperationTypeDTO innerReport: reportTechOperationTypeDTOList.stream()
                   .filter(f->(f.getTechOperationType()).equals(reportTechOperationTypeDTO.getTechOperationType()))
                   .collect(Collectors.toList())) {
               factAmount += innerReport.getFactAmount();
            }
            tempReport.setFactAmount(factAmount);
            Plan plan = planService.findByTechOperationId(reportTechOperationTypeDTO.getTechOperationId(),
                    dateFilterDTO.getStartDate());
            if (plan != null) {
                tempReport.setPlanAmount(plan.getAmount());
                tempReport.setImplementPlan((double) (factAmount / plan.getAmount()) * 100);
            }
            resultReportTechOperationTypeList.add(tempReport);
        }
        return resultReportTechOperationTypeList;
    }

    public List<ReportImplementDetailDTO> reportImplementDetail(DateFilterDTO dateFilterDTO) {
        log.trace(">>reportImplementDetail... dateFilterDTO={}", dateFilterDTO);
        List<TechOperation> techOperationList = techOperationService.findLastTechOperation();
        List<ExecutorProgram> executorProgramList = executorProgramService
                .findAllByStartAndEndDate(dateFilterDTO.getStartDate(), dateFilterDTO.getEndDate());
        List<ReportImplementDetailDTO> reportImplementDetailDTOList = new ArrayList<>();
        for (TechOperation techOperation : techOperationList) {
            Long factCount = executorProgramList.stream()
                    .filter(f -> f.getFileName().equals(techOperation.getFileNameProgram())).count();
            TechProcess techProcess =
                    techProcessService.findTechProcessByTechOperationId(techOperation.getId());
            if (techProcess != null) {
                ReportImplementDetailDTO reportImplementDetailDTO = new ReportImplementDetailDTO();
                reportImplementDetailDTO.setName(techProcess.getDetailName());
                reportImplementDetailDTO.setFactAmount(factCount);
                Plan plan = planService
                        .findByTechOperationId(techOperation.getId(), dateFilterDTO.getStartDate());
                if (plan != null) {
                    reportImplementDetailDTO.setPlanAmount(plan.getAmount());
                    if (plan.getAmount() != null) {
                        reportImplementDetailDTO.setImplementPlan((double)
                                (factCount / plan.getAmount()) * 100);
                    }
                }
                reportImplementDetailDTOList.add(reportImplementDetailDTO);
            }
        }
        return reportImplementDetailDTOList;
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
