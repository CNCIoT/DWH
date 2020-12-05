package com.stankin.collector.service;

import com.stankin.collector.domain.table.Device;
import com.stankin.collector.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device save(@NotNull Device device) {
        Optional<Device> deviceOptional = deviceRepository.findById(device.getId());
        if (deviceOptional.isPresent()) {
            return deviceRepository.save(device);
        }
        device.setId(null);
        return deviceRepository.save(device);
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> findById(@NotNull Long id) {
        return deviceRepository.findById(id);
    }

    public void delete(@NotNull Device device) {
        deviceRepository.delete(device);
    }
}
