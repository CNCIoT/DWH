package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.domain.TechOperationType;
import com.stankin.machine.core.dto.EmployeeReportFinishedDetailDTO;
import com.stankin.machine.core.dto.FinishedPartDTO;
import com.stankin.machine.core.dto.FinishedPartFilterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinishedPartReportService {

    private final ExecutorProgramService executorProgramService;
    private final EmployeeService employeeService;
    private final TechOperationTypeService techOperationTypeService;
    private final ReportFinishedDetailService reportFinishedDetailService;
    private final TechOperationService techOperationService;
    private final TechProcessService techProcessService;


    public FinishedPartReportService(ExecutorProgramService executorProgramService,
                                     EmployeeService employeeService,
                                     TechOperationTypeService techOperationTypeService,
                                     ReportFinishedDetailService reportFinishedDetailService,
                                     TechOperationService techOperationService, TechProcessService techProcessService) {
        this.executorProgramService = executorProgramService;
        this.employeeService = employeeService;
        this.techOperationTypeService = techOperationTypeService;
        this.reportFinishedDetailService = reportFinishedDetailService;
        this.techOperationService = techOperationService;
        this.techProcessService = techProcessService;
    }


    public List<FinishedPartDTO> report(Integer offset, Integer limit, FinishedPartFilterDTO dto) {
        log.trace(">>report... offset={}, limit={}, dto={}", offset, limit, dto);
        List<EmployeeReportFinishedDetailDTO> employeeList = reportFinishedDetailService
                .findAllEmployee(dto.getEmployeeId(), dto.getStartDate(), dto.getEndDate());
        List<EmployeeReportFinishedDetailDTO> employeeListLimited = employeeList;
        List<FinishedPartDTO> finishedPartDTOList = new ArrayList<>();

        if (offset != null && limit != null) {
            employeeListLimited = employeeList.stream()
                    .skip(offset)
                    .limit(limit)
                    .collect(Collectors.toList());
        }

        for (EmployeeReportFinishedDetailDTO employeeReportFinishedDetailDTO : employeeListLimited) {
            Optional<Employee> optionalEmployee = employeeService.findById(employeeReportFinishedDetailDTO.getEmployeeId());
            String empFullName = "";
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                empFullName = employeeService
                        .formatterFullName(employee.getFirstName(), employee.getLastName(), employee.getMiddleName());
            }
            double actualMachineTime = executorProgramService
                    .calculateActualMachineTime(employeeReportFinishedDetailDTO.getEmployeeId(),
                            employeeReportFinishedDetailDTO.getFileName(), dto.getStartDate(), dto.getEndDate());

            Optional<TechOperation> techOperationOptional = techOperationService.findByFileName(employeeReportFinishedDetailDTO.getFileName());
            String rownum = null;
            String techOperationName = null;
            Long techOperationTypeId = null;
            if (techOperationOptional.isPresent()) {
                TechOperation techOperation = techOperationOptional.get();
                rownum = techOperation.getRownum();
                techOperationName = techOperation.getName();
                techOperationTypeId = techOperation.getTechOperationTypeId();
            }
            Optional<TechOperationType> techOperationTypeOptional = techOperationTypeService.findById(techOperationTypeId);
            String techOperationTypeName = null;
            if(techOperationOptional.isPresent()){
                TechOperationType techOperationType = techOperationTypeOptional.get();
                techOperationTypeName = techOperationType.getName();
            }
            FinishedPartDTO finishedPartDTO = new FinishedPartDTO(empFullName, techOperationName, rownum,
                    techOperationTypeName, actualMachineTime,
                    employeeReportFinishedDetailDTO.getCountOperation());

            finishedPartDTOList.add(finishedPartDTO);
        }

        return finishedPartDTOList;
    }

}
