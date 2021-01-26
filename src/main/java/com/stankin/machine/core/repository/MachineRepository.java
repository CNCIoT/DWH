package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.Machine;
import org.springframework.data.repository.CrudRepository;

public interface MachineRepository extends CrudRepository<Machine, Long> {
}
