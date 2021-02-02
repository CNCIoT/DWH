package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.DateFilterDTO;
import com.stankin.machine.core.dto.report.ReportExecutePlanEmpDTO;
import com.stankin.machine.core.dto.report.ReportTechOperationTypeDTO;
import com.stankin.machine.core.service.PerformancePlanReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class PerformancePlanReportController {

    private final PerformancePlanReportService performancePlanReportService;

    public PerformancePlanReportController(PerformancePlanReportService performancePlanReportService) {
        this.performancePlanReportService = performancePlanReportService;
    }

    @PostMapping("/executePlanByEmp")
    public ResponseEntity<List<ReportExecutePlanEmpDTO>> reportExecutePlanByEmp(@RequestBody DateFilterDTO dateFilterDTO){
        List<ReportExecutePlanEmpDTO> executePlanEmpDTO = performancePlanReportService.reportExecutePlanByEmp(dateFilterDTO);
        return ResponseEntity.ok(executePlanEmpDTO);
    }

    @PostMapping("/planByTechOperationType")
    public ResponseEntity<List<ReportTechOperationTypeDTO>> reportPlanByTechOperationType(@RequestBody
                                                                                                      DateFilterDTO dateFilterDTO){
        List<ReportTechOperationTypeDTO> reportTechOperationTypeDTOList =
                performancePlanReportService.reportPlanByTechOperationType(dateFilterDTO);
        return ResponseEntity.ok(reportTechOperationTypeDTOList);
    }
}
