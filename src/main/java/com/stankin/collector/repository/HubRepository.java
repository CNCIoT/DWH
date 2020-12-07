package com.stankin.collector.repository;

import com.stankin.collector.domain.table.Hub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HubRepository extends CrudRepository<Hub, Long> {
    List<Hub> findAll();

}
