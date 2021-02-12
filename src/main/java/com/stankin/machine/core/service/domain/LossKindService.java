package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.LossKind;
import com.stankin.machine.core.repository.LossKindRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class LossKindService {

    private final LossKindRepository lossKindRepository;

    public LossKindService(LossKindRepository lossKindRepository) {
        this.lossKindRepository = lossKindRepository;
    }

    public Optional<LossKind> findById(Long id){
        log.trace(">>findById... id={}", id);
        return lossKindRepository.findById(id);
    }

    public LossKind save(LossKind lossKind){
        log.trace(">>save... lossKind={}", lossKind);
        Optional<LossKind> lossKindOptional = lossKindRepository.findById(lossKind.getId());
        if(lossKindOptional.isPresent()) {
            log.trace("update existing lossKind");
            lossKind.setUpdatedAt(new Date());
            return lossKindRepository.save(lossKind);
        }
        log.trace("create new loss kind");
        lossKind.setCreatedAt(new Date());
        lossKind.setUpdatedAt(new Date());
        lossKind.setId(null);
        return lossKindRepository.save(lossKind);
    }

    public void delete(@NotNull LossKind lossKind){
        log.trace(">>delete... lossKind={}", lossKind);
        lossKindRepository.delete(lossKind);
    }
}
