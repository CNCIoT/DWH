package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.ReasonIdle;
import com.stankin.machine.core.repository.ReasonIdleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class ReasonIdleService {

    private final ReasonIdleRepository reasonIdleRepository;

    public ReasonIdleService(ReasonIdleRepository reasonIdleRepository) {
        this.reasonIdleRepository = reasonIdleRepository;
    }

    public Optional<ReasonIdle> findById(Long id){
       log.trace(">>findById... id={}", id);
        return reasonIdleRepository.findById(id);
    }

    public ReasonIdle save(@NotNull ReasonIdle reasonIdle){
        log.trace(">>save... reasonIdle={}", reasonIdle);
        Optional<ReasonIdle> reasonIdleOptional = reasonIdleRepository.findById(reasonIdle.getId());
        if(reasonIdleOptional.isPresent()){
            log.trace("update existing reasonIdle...");
            reasonIdle.setUpdatedAt(new Date());
            return reasonIdleRepository.save(reasonIdle);
        }
        log.trace("create new reasonIdle...");
        reasonIdle.setCreatedAt(new Date());
        reasonIdle.setUpdatedAt(new Date());
        reasonIdle.setId(null);
        return reasonIdleRepository.save(reasonIdle);
    }

    public void delete(@NotNull ReasonIdle reasonIdle){
        log.trace(">>delete...reasonIdle={}", reasonIdle);
        reasonIdleRepository.delete(reasonIdle);
    }
}
