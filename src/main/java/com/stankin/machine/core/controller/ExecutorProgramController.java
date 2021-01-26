package com.stankin.machine.core.controller;

import com.stankin.machine.core.service.ExecutorProgramService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/executor/program")
public class ExecutorProgramController {

    private final ExecutorProgramService executorProgramService;

    public ExecutorProgramController(ExecutorProgramService executorProgramService) {
        this.executorProgramService = executorProgramService;
    }

    @PostMapping
    @Operation(summary = "сохранение сущности", description = "", responses = {

    })
    public ResponseEntity<Void> save(){
        return ResponseEntity.ok().build();
    }

}
