package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    List<Plan> findAll();
}
