package com.stankin.machine.core.dto;

import lombok.Data;

@Data
public class EmployeeReportFinishedDetailDTO {
    private Long employeeId;
    private String fileName;
    private int countOperation;
}
