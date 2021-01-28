package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.TechOperationType;
import com.stankin.machine.core.service.TechOperationTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/tech/operation/type")
public class TechOperationTypeController {

    private final TechOperationTypeService techOperationTypeService;

    public TechOperationTypeController(TechOperationTypeService techOperationTypeService) {
        this.techOperationTypeService = techOperationTypeService;
    }

    @PostMapping
    public ResponseEntity<TechOperationType> save(@NotNull @RequestBody TechOperationType techOperationType) {
        TechOperationType newTechOperationType = techOperationTypeService.save(techOperationType);
        return ResponseEntity.ok(newTechOperationType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechOperationType> findById(@NotNull @PathVariable("id") Long id) {
        Optional<TechOperationType> techOperationTypeOptional = techOperationTypeService.findById(id);
        return techOperationTypeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody TechOperationType techOperationType) {
        techOperationTypeService.delete(techOperationType);
        return ResponseEntity.noContent().build();
    }
}
