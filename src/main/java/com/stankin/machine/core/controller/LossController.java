package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Loss;
import com.stankin.machine.core.service.domain.LossService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/loss")
public class LossController {

    private final LossService lossService;

    public LossController(LossService lossService) {
        this.lossService = lossService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loss> findById(@NotNull @PathVariable("id") Long id){
        Optional<Loss> lossOptional = lossService.findById(id);
        return lossOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Loss> save(@NotNull @RequestBody Loss loss){
        return ResponseEntity.ok(loss);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody Loss loss){
        lossService.delete(loss);
        return ResponseEntity.noContent().build();
    }
}
