package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.dto.EmployeeReportFinishedDetailDTO;
import com.stankin.machine.core.dto.FinishedPartDTO;
import com.stankin.machine.core.dto.FinishedPartFilterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinishedPartReportService {

    private final ExecutorProgramService executorProgramService;
    private final EmployeeService employeeService;
    private final TechOperationTypeService techOperationTypeService;
    private final ReportFinishedDetailService reportFinishedDetailService;

    public FinishedPartReportService(ExecutorProgramService executorProgramService,
                                     EmployeeService employeeService,
                                     TechOperationTypeService techOperationTypeService,
                                     ReportFinishedDetailService reportFinishedDetailService) {
        this.executorProgramService = executorProgramService;
        this.employeeService = employeeService;
        this.techOperationTypeService = techOperationTypeService;
        this.reportFinishedDetailService = reportFinishedDetailService;
    }


    public List<FinishedPartDTO> report(Integer offset, Integer limit, FinishedPartFilterDTO dto) {
        List<EmployeeReportFinishedDetailDTO> employeeList = reportFinishedDetailService
                .findAllEmployee(dto.getEmployeeId(), dto.getStartDate(), dto.getEndDate());
        List<EmployeeReportFinishedDetailDTO> employeeListLimited = Collections.emptyList();
        List<FinishedPartDTO> finishedPartDTOList = new ArrayList<>();

        if (offset == null || limit == null) {
            employeeListLimited = employeeList;
        } else {
            employeeListLimited = employeeList.stream()
                    .skip(offset)
                    .limit(limit)
                    .collect(Collectors.toList());
        }

        for(EmployeeReportFinishedDetailDTO employeeReportFinishedDetailDTO : employeeListLimited){
            Optional<Employee> optionalEmployee = employeeService.findById(employeeReportFinishedDetailDTO.getEmployeeId());
            String empFullName = "";
            if(optionalEmployee.isPresent()){
                Employee employee = optionalEmployee.get();
                empFullName = employeeService
                        .formatterFullName(employee.getFirstName(), employee.getLastName(), employee.getMiddleName());
            }
            double actualMachineTime =  executorProgramService
                    .calculateActualMachineTime(employeeReportFinishedDetailDTO.getEmployeeId(),
                            dto.getFileName(), dto.getStartDate(), dto.getEndDate());
            FinishedPartDTO finishedPartDTO = new FinishedPartDTO(empFullName, null, null,
                    null, actualMachineTime,
                    employeeReportFinishedDetailDTO.getCountOperation());
            finishedPartDTOList.add(finishedPartDTO);
        }

        return finishedPartDTOList;
    }

}
