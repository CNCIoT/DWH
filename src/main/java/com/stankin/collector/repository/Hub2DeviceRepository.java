package com.stankin.collector.repository;

import com.stankin.collector.domain.table.Hub2Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Hub2DeviceRepository extends CrudRepository<Hub2Device, Long> {
    List<Hub2Device> findAll();
}
