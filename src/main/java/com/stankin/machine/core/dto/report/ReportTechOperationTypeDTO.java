package com.stankin.machine.core.dto.report;

import lombok.Data;

@Data
public class ReportTechOperationTypeDTO {
    private String techOperationType;
    private Long planAmount;
    private Long factAmount;
    private Double implementPlan;
}