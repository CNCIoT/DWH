package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Machine;
import com.stankin.machine.core.service.MachineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/machine")
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    @Operation(summary = "сохранение сущности", description = "", responses = {

    })
    public ResponseEntity<Machine> save(@NotNull @RequestBody Machine machine) {
        Machine newMachine = machineService.save(machine);
        return ResponseEntity.ok(newMachine);
    }
}
