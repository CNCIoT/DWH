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

    @Query("SELECT COUNT(*) FROM mdc.executor_programs \n" +
            "WHERE employee_id = :employee_id\n" +
            "AND file_name = :file_name")
    int findAllWithGroupByEmpAndFileName(@Param("employee_id") Long employeeId,
                                         @Param("file_name") String fileName);

    @Query("SELECT SUM(e.actual_machine_time) \n" +
            "FROM mdc.executor_programs e\n" +
            "WHERE (created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp)\n" +
            "            AND created_at <= COALESCE(:end_date, now()))\n" +
            "            AND (:file_name IS NULL OR file_name = :file_name)\n" +
            "            AND (:employee_id IS NULL OR employee_id = :employee_id)")
    Double calculateActualMachineTime(@Param("employee_id") Long employeeId,
                                  @Param("file_name") String fileName,
                                  @Param("start_date") Date startDate,
                                  @Param("end_date") Date endDate);

    @Query("SELECT * FROM mdc.executor_programs ex\n" +
            "WHERE (created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp) \n" +
            "      AND created_at <= COALESCE(:end_date, now()))")
    List<ExecutorProgram> findAllByStartAndEndDate(@Param("start_date") Date startDate,
                                                   @Param("end_date") Date endDate);

    @Query("SELECT * FROM mdc.executor_programs ex\n" +
            "WHERE  employee_id = :employee_id" +
            " AND (created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp) \n" +
            "      AND created_at <= COALESCE(:end_date, now()))")
    List<ExecutorProgram> findAllByEmpAndStartAndEndDate(@Param("employee_id") Long employeeId,
                                                         @Param("start_date") Date startDate,
                                                         @Param("end_date") Date endDate);
}
