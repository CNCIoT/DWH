package com.stankin.collector.dto.discovery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DeviceHubDTO {
    @JsonIgnore
    private Long deviceId;
    private String name;
    private String[] inputTypes;
}
