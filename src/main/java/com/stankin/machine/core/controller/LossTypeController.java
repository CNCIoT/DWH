package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.LossType;
import com.stankin.machine.core.service.LossTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/loss/type")
public class LossTypeController {

    private final LossTypeService lossTypeService;

    public LossTypeController(LossTypeService lossTypeService) {
        this.lossTypeService = lossTypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LossType> findById(@PathVariable("id") Long id) {
        Optional<LossType> lossTypeOptional = lossTypeService.findById(id);
        return lossTypeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LossType> save(@NotNull @RequestBody LossType lossType) {
        return ResponseEntity.ok(lossTypeService.save(lossType));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody LossType lossType) {
        lossTypeService.delete(lossType);
        return ResponseEntity.noContent().build();
    }
}
