package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.Machine;
import com.stankin.machine.core.repository.MachineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine save(@NotNull Machine machine) {
        log.trace(">>save... machine={}", machine);
        Optional<Machine> optionalMachine = machineRepository.findById(machine.getId());
        if (optionalMachine.isPresent()) {
            log.trace("update existing machine");
            machineRepository.save(machine);
        }
        log.trace("create new machine");
        machine.setId(null);
        return machineRepository.save(machine);
    }

    public Optional<Machine> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return machineRepository.findById(id);
    }

    public void delete(@NotNull Machine machine) {
        log.trace(">>delete... machine={}", machine);
        machineRepository.delete(machine);
    }

    public int findCountMachineLocationId(Long id) {
        log.trace(">>findCountMachineLocationId... id={}", id);
        return machineRepository.findCountMachineLocationId(id);
    }
}
