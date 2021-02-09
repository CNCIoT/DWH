package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Plan;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    List<Plan> findAll();

    @Query("SELECT actual_date FROM mdc.plans WHERE location_id = :location_id")
    List<Date> findByLocation(@Param("location_id") Long locationId);

    @Query("SELECT * FROM mdc.plans \n" +
            " WHERE cast(date_trunc('month', actual_date) as date) = cast(date_trunc('month', :start_date::timestamp) " +
            "as date)\n" +
            " AND tech_operation_id = :tech_operation_id")
    Plan findByTechOperationId(@Param("tech_operation_id") Long techOperationId,
                               @Param("start_date") Date startDate);
}
