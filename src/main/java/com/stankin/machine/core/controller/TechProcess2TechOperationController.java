package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.TechProcess2TechOperation;
import com.stankin.machine.core.service.domain.TechProcess2TechOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tech/process/tech/operation")
public class TechProcess2TechOperationController {

    private final TechProcess2TechOperationService techProcess2TechOperationService;

    public TechProcess2TechOperationController(TechProcess2TechOperationService techProcess2TechOperationService) {
        this.techProcess2TechOperationService = techProcess2TechOperationService;
    }

    @GetMapping
    public ResponseEntity<List<TechProcess2TechOperation>> findAll() {
        return ResponseEntity.ok(techProcess2TechOperationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechProcess2TechOperation> findById(@PathVariable("id") Long id) {
        Optional<TechProcess2TechOperation> operationOptional = techProcess2TechOperationService.findById(id);
        return operationOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TechProcess2TechOperation> save(@NotNull @RequestBody
                                                                  TechProcess2TechOperation techProcess2TechOperation) {
        return ResponseEntity.ok(techProcess2TechOperationService.save(techProcess2TechOperation));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody TechProcess2TechOperation techProcess2TechOperation) {
        techProcess2TechOperationService.delete(techProcess2TechOperation);
        return ResponseEntity.noContent().build();
    }
}
