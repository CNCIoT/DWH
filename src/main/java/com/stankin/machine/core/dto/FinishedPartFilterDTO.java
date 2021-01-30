package com.stankin.machine.core.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FinishedPartFilterDTO {
    private Long employeeId;
    private String operationTypeName;
    private String detailName;
    private String fileName;
    private Date startDate;
    private Date endDate;
}
