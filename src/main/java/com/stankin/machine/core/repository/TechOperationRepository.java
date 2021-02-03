package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.TechOperation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TechOperationRepository extends CrudRepository<TechOperation, Long> {

    @Query("SELECT * FROM tech_operations WHERE file_name_program = :file_name")
    TechOperation findByFileName(@Param("file_name") String fileName);

    @Query("SELECT * FROM mdc.tech_operations WHERE name = :name")
    TechOperation findByName(@Param("name") String name);
}
