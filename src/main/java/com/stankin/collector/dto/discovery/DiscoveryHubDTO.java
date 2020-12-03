package com.stankin.collector.dto.discovery;

import lombok.Data;

import java.util.List;

@Data
public class DiscoveryHubDTO {
    List<DeviceHubDTO> deviceList;
}
