package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.domain.TechOperationType;
import com.stankin.machine.core.dto.DateFilterDTO;
import com.stankin.machine.core.dto.report.ReportExecutePlanEmpDTO;
import com.stankin.machine.core.dto.report.ReportTechOperationTypeDTO;
import com.stankin.machine.core.repository.ReportPlanByTechOperationTypeRepository;
import com.stankin.machine.core.service.domain.EmployeeService;
import com.stankin.machine.core.service.domain.ExecutorProgramService;
import com.stankin.machine.core.service.domain.TechOperationService;
import com.stankin.machine.core.service.domain.TechOperationTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PerformancePlanReportService {

    private final ExecutorProgramService executorProgramService;
    private final EmployeeService employeeService;
    private final TechOperationService techOperationService;
    private final ReportPlanByTechOperationTypeRepository reportPlanByTechOperationTypeRepository;
    private final PlanService planService;
    private final TechOperationTypeService techOperationTypeService;

    public PerformancePlanReportService(ExecutorProgramService executorProgramService,
                                        EmployeeService employeeService,
                                        TechOperationService techOperationService,
                                        ReportPlanByTechOperationTypeRepository reportPlanByTechOperationTypeRepository,
                                        PlanService planService,
                                        TechOperationTypeService techOperationTypeService) {
        this.executorProgramService = executorProgramService;
        this.employeeService = employeeService;
        this.techOperationService = techOperationService;
        this.reportPlanByTechOperationTypeRepository = reportPlanByTechOperationTypeRepository;
        this.planService = planService;
        this.techOperationTypeService = techOperationTypeService;
    }

    public List<ReportExecutePlanEmpDTO> reportExecutePlanByEmp(@NotNull DateFilterDTO dateFilterDTO) {
        log.trace(">>reportExecutePlanByEmp... dateFilterDTO={}", dateFilterDTO);
        List<ReportExecutePlanEmpDTO> reportExecutePlanEmpDTOList = new ArrayList<>();
        List<Employee> employeeList = employeeService.findAllByStartAndEndDate(dateFilterDTO.getStartDate(), dateFilterDTO.getEndDate());
        for (Employee employee : employeeList) {
            double executePlanFact = executorProgramService.calculateActualMachineTime(employee.getId(), null,
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
        for (ReportTechOperationTypeDTO reportTechOperationTypeDTO : reportTechOperationTypeDTOList) {
            String techOperationTypeName = reportTechOperationTypeDTO.getTechOperationType();
            if (techOperationTypeName != null) {
                // Optional<TechOperationType> techOperationTypeOptional = techOperationTypeService
                //         .findByName(techOperationTypeName);
                //  if(techOperationTypeOptional.isPresent()){
                //      TechOperationType techOperationType = techOperationTypeOptional.get();
                //   techOperationService.
                //techOperationService.findByName(tec)
            }
        }

            //techOperationService.findByName(reportTechOperationTypeDTO)
        // executorProgramService.fi
        return reportTechOperationTypeDTOList;
    }
}
