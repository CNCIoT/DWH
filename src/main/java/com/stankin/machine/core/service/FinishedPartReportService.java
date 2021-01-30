package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.dto.FinishedPartDTO;
import com.stankin.machine.core.dto.FinishedPartFilterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FinishedPartReportService {

    private final ExecutorProgramService executorProgramService;
    private final EmployeeService employeeService;
    private final TechOperationTypeService techOperationTypeService;

    public FinishedPartReportService(ExecutorProgramService executorProgramService,
                                     EmployeeService employeeService,
                                     TechOperationTypeService techOperationTypeService) {
        this.executorProgramService = executorProgramService;
        this.employeeService = employeeService;
        this.techOperationTypeService = techOperationTypeService;
    }

    public List<FinishedPartDTO> report(Integer offset, Integer limit, FinishedPartFilterDTO dto) {
        log.trace(">>report... dto={}", dto);

        List<FinishedPartDTO> finishedPartDTOList = new ArrayList<>();
        Long employeeId = dto.getEmployeeId();
        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();
        String fileName = dto.getFileName();
        List<ExecutorProgram> executorProgramList = executorProgramService
                .findAllByFilter(employeeId, fileName, startDate, endDate);
        if (executorProgramList.isEmpty()) {
            log.trace("<<report... executors not found");
            return Collections.emptyList();
        }
        for (ExecutorProgram executorProgram : executorProgramList) {
            Optional<Employee> employeeOptional = employeeService.findById(executorProgram.getEmployeeId());
            String empFullName = "";
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                if (employee.getFirstName() != null) {
                    empFullName += employee.getFirstName();
                }
                if (employee.getLastName() != null) {
                    empFullName += " " + employee.getLastName();
                }
                if (employee.getMiddleName() != null) {
                    empFullName += " " + employee.getMiddleName();
                }
            }
            FinishedPartDTO finishedPartDTO = new FinishedPartDTO(empFullName.trim(), null, null,
                    executorProgram.getActualMachineTime(),
                    1);
            finishedPartDTOList.add(finishedPartDTO);
        }
        if (offset == null || limit == null) {
            return finishedPartDTOList;
        }
        return finishedPartDTOList.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }
}
