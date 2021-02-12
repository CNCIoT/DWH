package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.LossKind;
import com.stankin.machine.core.service.domain.LossKindService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/loss/kind")
public class LossKindController {

    private final LossKindService lossKindService;

    public LossKindController(LossKindService lossKindService) {
        this.lossKindService = lossKindService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LossKind> findById(@NotNull @PathVariable("id") Long id){
        Optional<LossKind> lossKindOptional = lossKindService.findById(id);
        return lossKindOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LossKind> save(@NotNull @RequestBody LossKind lossKind){
        return ResponseEntity.ok(lossKindService.save(lossKind));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody LossKind lossKind){
        lossKindService.delete(lossKind);
        return ResponseEntity.noContent().build();
    }
}
