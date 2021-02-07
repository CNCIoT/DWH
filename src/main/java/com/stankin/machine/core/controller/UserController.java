package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.InnerUser;
import com.stankin.machine.core.service.domain.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    public UserController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<InnerUser>> findAll(){
        return ResponseEntity.ok(customUserDetailsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InnerUser> findById(@PathVariable("id") Long id){
        Optional<InnerUser> innerUserOptional = customUserDetailsService.findById(id);
        return innerUserOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InnerUser> save(@NotNull @RequestBody InnerUser innerUser){
        return ResponseEntity.ok(customUserDetailsService.save(innerUser));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody InnerUser innerUser){
        customUserDetailsService.delete(innerUser);
        return ResponseEntity.noContent().build();
    }
}
