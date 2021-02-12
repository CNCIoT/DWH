package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.Loss;
import com.stankin.machine.core.repository.LossRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class LossService {

    private final LossRepository lossRepository;

    public LossService(LossRepository lossRepository) {
        this.lossRepository = lossRepository;
    }

    public Optional<Loss> findById(Long id){
        log.trace(">>findById... id={}", id);
        return lossRepository.findById(id);
    }

    public Loss save(@NotNull Loss loss){
        log.trace(">>save... loss={}", loss);
        Optional<Loss> lossOption = lossRepository.findById(loss.getId());
        if(lossOption.isPresent()){
            log.trace("update existing loss");
            loss.setUpdatedAt(new Date());
            return lossRepository.save(loss);
        }
        log.trace(">>create new loss");
        loss.setCreatedAt(new Date());
        loss.setUpdatedAt(new Date());
        loss.setId(null);
        return lossRepository.save(loss);
    }

    public void delete(@NotNull Loss loss){
        log.trace(">>delete... loss={}", loss);
        lossRepository.delete(loss);
    }
}
