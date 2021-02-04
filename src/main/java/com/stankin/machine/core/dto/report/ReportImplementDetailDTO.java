package com.stankin.machine.core.dto.report;

import lombok.Data;

@Data
public class ReportImplementDetailDTO {
    private String name;
    private Long factAmount;
    private Long planAmount;
    private Double implementPlan;
}
