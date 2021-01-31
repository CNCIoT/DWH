package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.TechProcess2TechOperation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TechProcess2TechOperationRepository extends CrudRepository<TechProcess2TechOperation, Long> {
    List<TechProcess2TechOperation> findAll();
}
