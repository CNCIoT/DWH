package com.stankin.collector.service;

import com.stankin.collector.domain.table.HubConfig;
import com.stankin.collector.repository.HubConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HubConfigService {

    private final HubConfigRepository hubConfigRepository;

    public HubConfigService(HubConfigRepository hubConfigRepository) {
        this.hubConfigRepository = hubConfigRepository;
    }

    public HubConfig save(@NotNull HubConfig hubConfig) {
        log.trace("Entering method save... hubConfig={}", hubConfig);
        Optional<HubConfig> hubConfigOptional = hubConfigRepository.findById(hubConfig.getId());
        if (hubConfigOptional.isPresent()) {
            log.trace("update existing hubConfig...");
            return hubConfigRepository.save(hubConfig);
        }
        hubConfig.setId(null);
        log.trace("create new hubConfig... entity={}", hubConfig);
        return hubConfigRepository.save(hubConfig);
    }

    public List<HubConfig> findAll() {
        log.trace("Entering method findAll...");
        return hubConfigRepository.findAll();
    }

    public Optional<HubConfig> findById(@NotNull Long id) {
        log.trace("Entering method findById... id={}", id);
        return hubConfigRepository.findById(id);
    }

    public void delete(@NotNull HubConfig hubConfig) {
        log.trace("entering method delete... hubConfig={}", hubConfig);
        hubConfigRepository.delete(hubConfig);
    }
}
