package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.ReasonIdle;
import com.stankin.machine.core.service.domain.ReasonIdleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/reasonidle")
public class ReasonIdleController {

    private final ReasonIdleService reasonIdleService;

    public ReasonIdleController(ReasonIdleService reasonIdleService) {
        this.reasonIdleService = reasonIdleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReasonIdle> findById(@NotNull @PathVariable("id") Long id){
        Optional<ReasonIdle> reasonIdleOptional = reasonIdleService.findById(id);
        return reasonIdleOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReasonIdle> save(@NotNull @RequestBody ReasonIdle reasonIdle){
        return ResponseEntity.ok(reasonIdleService.save(reasonIdle));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody ReasonIdle reasonIdle){
        reasonIdleService.delete(reasonIdle);
        return ResponseEntity.noContent().build();
    }
}
