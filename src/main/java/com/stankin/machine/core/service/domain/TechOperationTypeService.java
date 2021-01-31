package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.TechOperationType;
import com.stankin.machine.core.repository.TechOperationTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class TechOperationTypeService {

    private final TechOperationTypeRepository techOperationTypeRepository;

    public TechOperationTypeService(TechOperationTypeRepository techOperationTypeRepository) {
        this.techOperationTypeRepository = techOperationTypeRepository;
    }

    public TechOperationType save(@NotNull @RequestBody TechOperationType techOperationType){
        log.trace(">>save.. techOperationType={}", techOperationType);
        Optional<TechOperationType> techOperationTypeOptional = techOperationTypeRepository
                .findById(techOperationType.getId());
        if(techOperationTypeOptional.isPresent()){
            log.trace("update existing tech operation type");
            return techOperationTypeRepository.save(techOperationType);
        }
        log.trace("create new tech operation type");
        techOperationType.setId(null);
        return techOperationTypeRepository.save(techOperationType);

    }

    public Optional<TechOperationType> findById(Long id) {
        log.trace(">>findById... id={}", id);
        return techOperationTypeRepository.findById(id);
    }

    public void delete(@NotNull TechOperationType techOperationType){
        log.trace(">>delete... techOperationType={}", techOperationType);
        techOperationTypeRepository.delete(techOperationType);
    }
}
