package com.stankin.collector.repository;

import com.stankin.collector.domain.table.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    List<Device> findAll();
}
