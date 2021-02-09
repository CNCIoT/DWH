package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Plan;
import com.stankin.machine.core.repository.PlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> findAll() {
        log.trace(">>findAll...");
        return planRepository.findAll();
    }

    public Optional<Plan> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return planRepository.findById(id);
    }

    public Plan save(@NotNull Plan plan) {
        log.trace(">>save... plan={}", plan);
        Optional<Plan> planOptional = planRepository.findById(plan.getId());
        if (planOptional.isPresent()) {
            log.trace("update existing plan");
            plan.setUpdatedAt(new Date());
            return planRepository.save(plan);
        }
        log.trace("create new plan");
        plan.setId(null);
        plan.setCreatedAt(new Date());
        plan.setUpdatedAt(new Date());
        return planRepository.save(plan);
    }

    public void delete(@NotNull Plan plan) {
        log.trace(">>delete... plan={}", plan);
        planRepository.delete(plan);
    }

    public List<Date> findByLocation(Long locationId) {
        log.trace(">>findByLocation... locationId={}", locationId);
        return planRepository.findByLocation(locationId);
    }

    public Plan findByTechOperationId(Long techOperationId, Date startDate) {
        log.trace(">>findByTechOperationId... techOperationId={}, startDate={}", techOperationId, startDate);
        return planRepository.findByTechOperationId(techOperationId, startDate);
    }

}
