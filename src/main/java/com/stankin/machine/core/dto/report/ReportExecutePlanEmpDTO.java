package com.stankin.machine.core.dto.report;

import lombok.Data;

@Data
public class ReportExecutePlanEmpDTO {
    private final String fullName;
    private final Double amountPlan;
    private final Double amountFact;
    private final Double implementPlan;
}
