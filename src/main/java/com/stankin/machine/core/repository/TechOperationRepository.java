package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.TechOperation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TechOperationRepository extends CrudRepository<TechOperation, Long> {

    @Query("SELECT * FROM tech_operations WHERE file_name_program = :file_name")
    TechOperation findByFileName(@Param("file_name") String fileName);

    @Query("SELECT * FROM mdc.tech_operations WHERE name = :name")
    TechOperation findByName(@Param("name") String name);

    @Query("SELECT tech_op.*\n" +
            "FROM mdc.tech_process2tech_operations tpt \n" +
            "JOIN mdc.tech_operations tech_op ON tech_op.id = tpt.tech_operation_id\n" +
            "WHERE tech_op.rownum = (\n" +
            "SELECT MAX(tech_op1.rownum) \n" +
            "FROM mdc.tech_process2tech_operations tpt1\n" +
            "JOIN mdc.tech_operations tech_op1 ON tech_op1.id = tpt1.tech_operation_id\n" +
            "WHERE tpt1.tech_process_id = tpt.tech_process_id)\n")
    List<TechOperation> findLastTechOperation();
}
