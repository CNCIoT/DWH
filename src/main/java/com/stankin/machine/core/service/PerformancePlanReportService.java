package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.dto.DateFilterDTO;
import com.stankin.machine.core.dto.report.ReportExecutePlanEmpDTO;
import com.stankin.machine.core.dto.report.ReportTechOperationTypeDTO;
import com.stankin.machine.core.service.domain.EmployeeService;
import com.stankin.machine.core.service.domain.ExecutorProgramService;
import com.stankin.machine.core.service.domain.TechOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PerformancePlanReportService {

    private final ExecutorProgramService executorProgramService;
    private final EmployeeService employeeService;
    private final TechOperationService techOperationService;

    public PerformancePlanReportService(ExecutorProgramService executorProgramService,
                                        EmployeeService employeeService,
                                        TechOperationService techOperationService) {
        this.executorProgramService = executorProgramService;
        this.employeeService = employeeService;
        this.techOperationService = techOperationService;
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

       // executorProgramService.fi
        return null;
    }
}
