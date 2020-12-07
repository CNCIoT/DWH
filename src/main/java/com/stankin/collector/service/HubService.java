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
            try {
              return  hubJdbcRepository.save(hub, null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
    }

    private void updateDeviceListAvailable(@NotNull final int id, @NotNull final String deviceListAvailable) {

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

    public Hub mockHub() {
        Hub hub = new Hub();
        hub.setId(-1L);
        hub.setCreatedAt(new Date());
        hub.setCreatedAt(new Date());
        return hub;
    }
}
