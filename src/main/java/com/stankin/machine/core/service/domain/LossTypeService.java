package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.LossType;
import com.stankin.machine.core.repository.LossTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class LossTypeService {

    private final LossTypeRepository lossTypeRepository;

    public LossTypeService(LossTypeRepository lossTypeRepository) {
        this.lossTypeRepository = lossTypeRepository;
    }

    public Optional<LossType> findById(Long id){
        log.trace(">>findById... id={}", id);
        return lossTypeRepository.findById(id);
    }

    public LossType save(LossType lossType){
        log.trace(">>save... lossType={}", lossType);
        Optional<LossType> lossTypeOptional = lossTypeRepository.findById(lossType.getId());
        if(lossTypeOptional.isPresent()){
            log.trace("update existing lossType");
            lossType.setUpdatedAt(new Date());
            return lossTypeRepository.save(lossType);
        }
        log.trace("create new lossType");
        lossType.setCreatedAt(new Date());
        lossType.setUpdatedAt(new Date());
        lossType.setId(null);
        return lossTypeRepository.save(lossType);
    }

    public void delete(@NotNull LossType lossType){
        log.trace(">>delete... lossType={}", lossType);
        lossTypeRepository.delete(lossType);
    }


}
