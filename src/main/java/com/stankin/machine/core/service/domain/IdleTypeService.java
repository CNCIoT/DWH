package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.IdleType;
import com.stankin.machine.core.repository.IdleTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IdleTypeService {

    private final IdleTypeRepository idleTypeRepository;

    public IdleTypeService(IdleTypeRepository idleTypeRepository) {
        this.idleTypeRepository = idleTypeRepository;
    }

    public IdleType save(IdleType idleType){
        log.trace(">>save... idleType={}", idleType);
        Optional<IdleType> idleTypeOptional = idleTypeRepository.findById(idleType.getId());
        if(idleTypeOptional.isPresent()){
            log.trace("update existing idleType...");
            idleType.setUpdatedAt(new Date());
            return idleTypeRepository.save(idleType);
        }
        log.trace("create new idleType...");
        idleType.setId(null);
        idleType.setCreatedAt(new Date());
        idleType.setUpdatedAt(new Date());
        return idleTypeRepository.save(idleType);
    }

    public Optional<IdleType> findById(Long id){
        log.trace(">>findById... id={}", id);
        return idleTypeRepository.findById(id);
    }

    public List<IdleType> findAll(){
        log.trace(">>findAll...");
        return idleTypeRepository.findAll();
    }

    public void delete(IdleType idleType) {
        log.trace(">>delete... idleType={}", idleType);
        idleTypeRepository.delete(idleType);
    }

    public IdleType findByKind(String kind) {
        log.trace(">>findByKind.. kind={}", kind);
        return idleTypeRepository.findByKind(kind);
    }
}
