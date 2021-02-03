package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.TechOperationType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TechOperationTypeRepository extends CrudRepository<TechOperationType, Long> {
    @Query("SELECT * FROM mdc.tech_operation_types WHERE name = :name;")
    TechOperationType findByName(@Param("name") String name);
}
