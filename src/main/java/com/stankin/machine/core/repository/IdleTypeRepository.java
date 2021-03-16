package com.stankin.machine.core.repository;


import com.stankin.machine.core.domain.IdleType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdleTypeRepository extends CrudRepository<IdleType, Long> {
    List<IdleType> findAll();

    @Query("SELECT * FROM idle_types WHERE code = :kind")
    IdleType findByKind(@Param("kind") String kind);
}
