package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Machine;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface MachineRepository extends CrudRepository<Machine, Long> {

    @Query("SELECT COUNT(*) FROM machines WHERE location_id = :location_id")
    int findCountMachineLocationId(@Param("location_id") Long locationId);
}
