package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Machine;
import com.stankin.machine.core.service.MachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/machine")
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    public ResponseEntity<Machine> save(@NotNull @RequestBody Machine machine) {
        Machine newMachine = machineService.save(machine);
        return ResponseEntity.ok(newMachine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> findById(@NotNull @PathVariable("id") Long id) {
        Optional<Machine> machineOptional = machineService.findById(id);
        return machineOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody Machine machine) {
        machineService.delete(machine);
        return ResponseEntity.noContent().build();
    }
}
