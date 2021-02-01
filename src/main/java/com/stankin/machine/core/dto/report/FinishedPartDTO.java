package com.stankin.machine.core.dto.report;

import lombok.Data;

@Data
public class FinishedPartDTO {
    private final String operatorFullName;
    private final String detailName;
    private final String rowNumOperation;
    private final String operationTypeName;
    private final Double actualMachineTime;
    private final int countOperation;
}
