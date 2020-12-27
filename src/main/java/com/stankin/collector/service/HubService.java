package com.stankin.collector.service;

import com.stankin.collector.domain.table.Hub;
import com.stankin.collector.repository.HubJdbcRepository;
import com.stankin.collector.repository.HubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HubService {

    private final HubRepository hubRepository;
    private final HubJdbcRepository hubJdbcRepository;

    public HubService(HubRepository hubRepository,
                      HubJdbcRepository hubJdbcRepository) {
        this.hubRepository = hubRepository;
        this.hubJdbcRepository = hubJdbcRepository;
    }

    public Hub save(@NotNull Hub hub) {
        log.info(">>save... hub={}", hub);
        try {
            return hubJdbcRepository.save(hub);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void updateDeviceListAvailable(@NotNull final Long id, @NotNull final String deviceListAvailable) {
        log.trace(">>updateDeviceListAvailable... id={}, deviceListAvailable={}", id, deviceListAvailable);
        hubJdbcRepository.updateDeviceListAvailable(id, deviceListAvailable);
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


    public Hub mockHub(String deviceListAvailable) {
        Hub hub = new Hub();
        hub.setId(-1L);
        hub.setName("new Hub. Update it");
        hub.setDeviceListAvailable(deviceListAvailable);
        hub.setCreatedAt(new Date());
        hub.setCreatedAt(new Date());
        return hub;
    }
}
