package com.stankin.collector.repository;

import com.stankin.collector.domain.table.HubConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HubConfigRepository extends CrudRepository<HubConfig, Long> {
    List<HubConfig> findAll();
}
