package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.IdleType;
import com.stankin.machine.core.service.domain.IdleTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/idle/type")
public class IdleTypeController {

    private final IdleTypeService idleTypeService;

    public IdleTypeController(IdleTypeService idleTypeService) {
        this.idleTypeService = idleTypeService;
    }

    @PostMapping
    public ResponseEntity<IdleType> save(@NotNull @RequestBody IdleType idleType){
        return ResponseEntity.ok(idleTypeService.save(idleType));
    }

    @GetMapping
    public ResponseEntity<List<IdleType>> findAll(){
        return ResponseEntity.ok(idleTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdleType> findById(@PathVariable("id") Long id){
       Optional<IdleType> idleTypeOptional = idleTypeService.findById(id);
        return idleTypeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody IdleType idleType){
        idleTypeService.delete(idleType);
        return ResponseEntity.noContent().build();
    }
}
