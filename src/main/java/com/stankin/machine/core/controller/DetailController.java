package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Detail;
import com.stankin.machine.core.service.DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/detail")
public class DetailController {

    private final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping
    public ResponseEntity<Detail> save(@NotNull @RequestBody Detail detail){
        Detail newDetail = detailService.save(detail);
        return ResponseEntity.ok(newDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail> findById(@NotNull @PathVariable("id") Long id){
        Optional<Detail> detailOptional = detailService.findById(id);
        return detailOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody Detail detail){
        detailService.delete(detail);
        return ResponseEntity.noContent().build();
    }
}
