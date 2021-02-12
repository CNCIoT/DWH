package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.ReasonIdle2IdleType;
import com.stankin.machine.core.repository.ReasonIdle2IdleTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class ReasonIdle2IdleTypeService {

    private final ReasonIdle2IdleTypeRepository repository;

    public ReasonIdle2IdleTypeService(ReasonIdle2IdleTypeRepository repository) {
        this.repository = repository;
    }

    public Optional<ReasonIdle2IdleType> findById(Long id){
        log.trace(">>findById... id={}", id);
        return repository.findById(id);
    }

    public ReasonIdle2IdleType save(@NotNull ReasonIdle2IdleType reasonIdle2IdleType){
        log.trace(">>save... reasonIdle2IdleType={}", reasonIdle2IdleType);
        Optional<ReasonIdle2IdleType> reasonIdle2IdleTypeOptional = repository.findById(reasonIdle2IdleType.getId());
        if(reasonIdle2IdleTypeOptional.isPresent()){
            log.trace("update existing ReasonIdle2IdleType");
            reasonIdle2IdleType.setUpdatedAt(new Date());
            return repository.save(reasonIdle2IdleType);
        }
        log.trace("create new ReasonIdle2IdleType");
        reasonIdle2IdleType.setCreatedAt(new Date());
        reasonIdle2IdleType.setUpdatedAt(new Date());
        reasonIdle2IdleType.setId(null);
        return repository.save(reasonIdle2IdleType);
    }

    public void delete(@NotNull ReasonIdle2IdleType reasonIdle2IdleType){
        log.trace(">>delete... reasonIdle2IdleType={}", reasonIdle2IdleType);
        repository.delete(reasonIdle2IdleType);
    }
}
