package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.TechProcess;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TechProcessRepository extends CrudRepository<TechProcess, Long> {

    @Query("SELECT DISTINCT tp.* FROM mdc.tech_operations tech_o\n" +
            "JOIN mdc.tech_process2tech_operations p2p ON tech_o.id = p2p.tech_operation_id\n" +
            "JOIN mdc.tech_processes tp ON tp.id = p2p.tech_process_id" +
            " WHERE (:id IS NULL OR tech_o.id = :id)")
    TechProcess findByTechOperationId(@Param("id") Long id);

    @Query("SELECT tp.* FROM mdc.tech_process2tech_operations tpt\n" +
            "JOIN mdc.tech_processes tp ON tp.id = tpt.tech_process_id\n" +
            "WHERE tpt.tech_operation_id = :tech_operation_id")
    TechProcess findTechProcessByTechOperationId(@Param("tech_operation_id") Long techOperationId);
}
