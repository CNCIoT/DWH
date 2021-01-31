package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.service.TechOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/tech/operation")
public class TechOperationController {

    private final TechOperationService techOperationService;

    public TechOperationController(TechOperationService techOperationService) {
        this.techOperationService = techOperationService;
    }

    @PostMapping
    public ResponseEntity<TechOperation> save(@NotNull @RequestBody TechOperation techOperation) {
        TechOperation newTechOperation = techOperationService.save(techOperation);
        return ResponseEntity.ok(newTechOperation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechOperation> findById(@NotNull @PathVariable("id") Long id) {
        Optional<TechOperation> techOperationOptional = techOperationService.findById(id);
        return techOperationOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody TechOperation techOperation) {
        techOperationService.delete(techOperation);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/file/name/{fileName}")
    public ResponseEntity<TechOperation> findByName(@PathVariable("fileName") String fileName){
        Optional<TechOperation> techOperationOptional = techOperationService.findByFileName(fileName);
        return techOperationOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
