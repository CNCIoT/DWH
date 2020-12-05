package com.stankin.collector.converter;

import com.stankin.collector.domain.table.Device;
import com.stankin.collector.dto.discovery.DeviceHubDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeviceHubDTOToDeviceConverter implements Converter<DeviceHubDTO, Device> {
    @Override
    public Device convert(DeviceHubDTO deviceHubDTO) {
        Device device = new Device();
        device.setId(-1L);
        device.setCreatedAt(new Date());
        device.setUpdatedAt(new Date());
        device.setDeviceTypeId(null);
        device.setName(deviceHubDTO.getName());
        return device;
    }
}
