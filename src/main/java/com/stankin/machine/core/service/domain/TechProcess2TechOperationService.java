package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.TechProcess2TechOperation;
import com.stankin.machine.core.repository.TechProcess2TechOperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TechProcess2TechOperationService {

    private final TechProcess2TechOperationRepository techProcess2TechOperationRepository;

    public TechProcess2TechOperationService(TechProcess2TechOperationRepository techProcess2TechOperationRepository) {
        this.techProcess2TechOperationRepository = techProcess2TechOperationRepository;
    }

    public List<TechProcess2TechOperation> findAll() {
        log.trace(">>findAll...");
        return techProcess2TechOperationRepository.findAll();
    }

    public Optional<TechProcess2TechOperation> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return techProcess2TechOperationRepository.findById(id);
    }

    public TechProcess2TechOperation save(@NotNull TechProcess2TechOperation techOperation) {
        log.trace(">>save... techProcess2TechOperation={}", techOperation);
        return techProcess2TechOperationRepository.save(techOperation);
    }

    public void delete(@NotNull TechProcess2TechOperation techOperation) {
        log.trace(">>delete... techProcess2TechOperation={}", techOperation);
        techProcess2TechOperationRepository.delete(techOperation);
    }

}
