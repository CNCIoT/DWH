package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.domain.TechProcess;
import com.stankin.machine.core.repository.TechProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class TechProcessService {

    private final TechProcessRepository techProcessRepository;

    public TechProcessService(TechProcessRepository techProcessRepository) {
        this.techProcessRepository = techProcessRepository;
    }

    public TechProcess save(@NotNull TechProcess techProcess) {
        log.trace(">>save... techProcess={}", techProcess);
        Optional<TechProcess> techProcessOptional = techProcessRepository.findById(techProcess.getId());
        if (techProcessOptional.isPresent()) {
            log.trace("update existing tech process");
            return techProcessRepository.save(techProcess);
        }
        log.trace("create new tech process");
        techProcess.setId(null);
        return techProcessRepository.save(techProcess);
    }

    public Optional<TechProcess> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return techProcessRepository.findById(id);
    }

    public void delete(@NotNull TechProcess techProcess) {
        log.trace(">>delete... techProcess={}", techProcess);
        techProcessRepository.delete(techProcess);
    }

    public Optional<TechProcess> findByTechOperationId(Long id){
        log.trace(">>findByTechOperationId... id={}", id);
        TechProcess techProcess = techProcessRepository.findByTechOperationId(id);
        return Optional.of(techProcess);
    }

    public TechProcess findTechProcessByTechOperationId(Long techOperationId) {
        log.trace(">>findTechProcessByTechOperationId... techOperationId}", techOperationId);
        return techProcessRepository.findTechProcessByTechOperationId(techOperationId);
    }
}
