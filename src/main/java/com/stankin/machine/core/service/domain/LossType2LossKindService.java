package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.repository.LossType2LossKindRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class LossType2LossKindService {

    private final LossType2LossKindRepository repository;

    public LossType2LossKindService(LossType2LossKindRepository repository) {
        this.repository = repository;
    }

    public Optional<LossType2LossKind> findById(Long id) {
        log.trace(">>findById... id={}", id);
        return repository.findById(id);
    }

    public LossType2LossKind save(@NotNull LossType2LossKind lossType2LossKind) {
        log.trace(">>save... lossType2LossKind={}", lossType2LossKind);
        Optional<LossType2LossKind> lossType2LossKindOptional = repository.findById(lossType2LossKind.getId());
        if (lossType2LossKindOptional.isPresent()) {
            log.trace("update existing lossType2LossKind");
            lossType2LossKind.setUpdatedAt(new Date());
            return repository.save(lossType2LossKind);
        }
        log.trace("create new lossType2LossKind");
        lossType2LossKind.setCreatedAt(new Date());
        lossType2LossKind.setUpdatedAt(new Date());
        lossType2LossKind.setId(null);
        return repository.save(lossType2LossKind);
    }

    public void delete(@NotNull LossType2LossKind lossType2LossKind) {
        log.trace(">>delete... lossType2LossKind={}", lossType2LossKind);
        repository.delete(lossType2LossKind);
    }
}
