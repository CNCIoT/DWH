package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.TechProcess;
import com.stankin.machine.core.service.TechProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/tech/process")
public class TechProcessController {

    private final TechProcessService techProcessService;

    public TechProcessController(TechProcessService techProcessService) {
        this.techProcessService = techProcessService;
    }

    @PostMapping
    public ResponseEntity<TechProcess> save(@NotNull @RequestBody TechProcess techProcess) {
        TechProcess newTechProcess = techProcessService.save(techProcess);
        return ResponseEntity.ok(newTechProcess);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechProcess> findById(@NotNull @PathVariable("id") Long id) {
        Optional<TechProcess> techProcessOptional = techProcessService.findById(id);
        return techProcessOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody TechProcess techProcess) {
        techProcessService.delete(techProcess);
        return ResponseEntity.noContent().build();
    }


}
