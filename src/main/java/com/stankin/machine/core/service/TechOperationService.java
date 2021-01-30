package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.repository.TechOperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class TechOperationService {

    private final TechOperationRepository techOperationRepository;

    public TechOperationService(TechOperationRepository techOperationRepository) {
        this.techOperationRepository = techOperationRepository;
    }

    public TechOperation save(@NotNull TechOperation techOperation) {
        log.trace(">>save... techOperation={}", techOperation);
        Optional<TechOperation> techOperationOptional = techOperationRepository.findById(techOperation.getId());
        if (techOperationOptional.isPresent()) {
            log.trace("update existing tech operation");
            return techOperationRepository.save(techOperation);
        }
        techOperation.setId(null);
        techOperation.setCreatedAt(new Date());
        techOperation.setUpdatedAt(new Date());
        TechOperation newTechOperation = techOperationRepository.save(techOperation);
        log.trace("<<save...create new tech operation. new tech operation: {}", newTechOperation);
        return newTechOperation;
    }

    public Optional<TechOperation> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return techOperationRepository.findById(id);
    }

    public void delete(@NotNull TechOperation techOperation) {
        log.trace(">>delete... techOperation={}", techOperation);
        techOperationRepository.delete(techOperation);
    }

}
