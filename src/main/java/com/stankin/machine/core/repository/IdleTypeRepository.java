package com.stankin.machine.core.repository;


import com.stankin.machine.core.domain.IdleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IdleTypeRepository extends CrudRepository<IdleType, Long> {
    List<IdleType> findAll();
}
