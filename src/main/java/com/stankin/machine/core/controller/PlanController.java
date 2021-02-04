package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Plan;
import com.stankin.machine.core.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<Plan> save(@NotNull @RequestBody Plan plan) {
        return ResponseEntity.ok(planService.save(plan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> findById(@NotNull @PathVariable("id") Long id) {
        Optional<Plan> planOptional = planService.findById(id);
        return planOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Plan>> findAll() {
        return ResponseEntity.ok(planService.findAll());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody Plan plan) {
        planService.delete(plan);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findPlanDateByLocationId")
    public ResponseEntity<List<Date>> findByLocation(@NotNull @RequestParam("locationId") Long locationId){
        List<Date> dateList = planService.findByLocation(locationId);
        return ResponseEntity.ok(dateList);
    }

}
