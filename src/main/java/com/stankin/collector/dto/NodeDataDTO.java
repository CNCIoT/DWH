package com.stankin.collector.dto;

import lombok.Data;

import java.util.List;

@Data
public class NodeDataDTO {
    private Long deviceId;
    private List<DevicesDataDTO> devicesData;
}
