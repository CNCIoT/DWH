package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findAll();
}
