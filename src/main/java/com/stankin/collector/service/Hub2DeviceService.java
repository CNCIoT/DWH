package com.stankin.collector.service;

import com.stankin.collector.domain.table.Hub2Device;
import com.stankin.collector.repository.Hub2DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class Hub2DeviceService {

    private final Hub2DeviceRepository hub2DeviceRepository;

    public Hub2DeviceService(Hub2DeviceRepository hub2DeviceRepository) {
        this.hub2DeviceRepository = hub2DeviceRepository;
    }

    public Hub2Device save(@NotNull Hub2Device hub2Device) {
        log.trace("Entering method save... hub2Device={}", hub2Device);
        Optional<Hub2Device> hub2DeviceOptional = hub2DeviceRepository.findById(hub2Device.getId());
        if (hub2DeviceOptional.isPresent()) {
            log.trace("update existing hub2device...");
            return hub2DeviceRepository.save(hub2Device);
        }
        hub2Device.setId(null);
        log.trace("create new hub2device... entity={}", hub2Device);
        return hub2DeviceRepository.save(hub2Device);
    }

    public List<Hub2Device> findAll() {
        log.trace("Entering method findAll...");
        return hub2DeviceRepository.findAll();
    }

    public Optional<Hub2Device> findById(@NotNull Long id) {
        log.trace("Entering method findById... id={}", id);
        return hub2DeviceRepository.findById(id);
    }

    public void delete(@NotNull Hub2Device hub2Device) {
        log.trace("Entering method delete... hub2Device={}", hub2Device);
        hub2DeviceRepository.delete(hub2Device);
    }


}
