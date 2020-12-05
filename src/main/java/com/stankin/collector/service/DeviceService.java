package com.stankin.collector.service;

import com.stankin.collector.domain.table.Device;
import com.stankin.collector.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device save(@NotNull Device device) {
        log.trace("entering method save... device={}", device);
        Optional<Device> deviceOptional = deviceRepository.findById(device.getId());
        if (deviceOptional.isPresent()) {
            log.trace("create new device... device={}", device);
            return deviceRepository.save(device);
        }
        device.setId(null);
        log.trace("create new device... device={}", device);
        return deviceRepository.save(device);
    }

    public List<Device> findAll() {
        log.trace("entering method findAll...");
        return deviceRepository.findAll();
    }

    public Optional<Device> findById(@NotNull Long id) {
        log.trace("entering method findById... id={}", id);
        return deviceRepository.findById(id);
    }

    public void delete(@NotNull Device device) {
        log.trace("entering method delete... device={}", device);
        deviceRepository.delete(device);
    }
}
