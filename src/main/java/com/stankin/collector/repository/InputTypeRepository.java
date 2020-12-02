package com.stankin.collector.repository;

import com.stankin.collector.domain.table.InputTypes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 */
public interface InputTypeRepository extends CrudRepository<InputTypes, Long> {

    List<InputTypes> findAll();

    InputTypes findByKind(String kind);
}
