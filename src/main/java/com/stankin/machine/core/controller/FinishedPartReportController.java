package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.FinishedPartDTO;
import com.stankin.machine.core.dto.FinishedPartFilterDTO;
import com.stankin.machine.core.service.FinishedPartReportService;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report/finished/part")
public class FinishedPartReportController {

    private final FinishedPartReportService finishedPartReportService;

    public FinishedPartReportController(FinishedPartReportService finishedPartReportService) {
        this.finishedPartReportService = finishedPartReportService;
    }

    @PostMapping
    public ResponseEntity<List<FinishedPartDTO>> report(@RequestParam(value = "offset", required = false) Integer offset,
                                                        @RequestParam(value = "limit", required = false) Integer limit,
                                                        @RequestBody FinishedPartFilterDTO dto) {
        if (dto == null) dto = new FinishedPartFilterDTO();
        List<FinishedPartDTO> response = finishedPartReportService.report(offset, limit, dto);
        return ResponseEntity.ok(response);
    }
}
