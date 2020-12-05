package com.stankin.collector.service;

import com.stankin.collector.domain.table.Hub;
import com.stankin.collector.repository.HubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HubService {

    private final HubRepository hubRepository;

    public HubService(HubRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    public Hub save(@NotNull Hub hub) {
        Optional<Hub> hubOptional = hubRepository.findById(hub.getId());
        if (hubOptional.isPresent()) {
            log.trace("update existing hub(id:{})={}", hub.getId(), hub);
            return hubRepository.save(hub);
        }
        hub.setId(null);
        log.trace("create new hub={}", hub);
        return hubRepository.save(hub);
    }

    public List<Hub> findAll() {
        return hubRepository.findAll();
    }

    public Optional<Hub> findById(@NotNull Long id) {
        log.trace("Entering method findById... id={}", id);
        return hubRepository.findById(id);
    }

    public void delete(@NotNull Hub hub) {
        log.trace("Entering method delete... hub={}", hub);
        hubRepository.delete(hub);
    }

    public Hub mockHub(){
        Hub hub = new Hub();
        hub.setId(-1L);
        hub.setCreatedAt(new Date());
        hub.setCreatedAt(new Date());
        return hub;
    }
}
