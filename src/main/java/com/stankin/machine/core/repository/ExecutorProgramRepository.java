package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.ExecutorProgram;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExecutorProgramRepository extends CrudRepository<ExecutorProgram, Long> {

    @Query("SELECT *  FROM mdc.executor_programs \n" +
            "WHERE (created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp) \n" +
            "\tAND created_at <= COALESCE(:end_date, now()))\n" +
            "AND (:file_name IS NULL OR file_name = :file_name)\n" +
            "AND (:employee_id IS NULL OR employee_id = :employee_id)" +
            "ORDER BY created_at DESC")
    List<ExecutorProgram> findAllByFilter(@Param("employee_id") Long employeeId,
                                          @Param("file_name") String fileName,
                                          @Param("start_date") Date startDate,
                                          @Param("end_date") Date endDate);
}
