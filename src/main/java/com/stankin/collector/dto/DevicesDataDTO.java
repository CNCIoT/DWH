package com.stankin.collector.dto;

import com.stankin.collector.domain.Values;
import lombok.Data;

@Data
public class DevicesDataDTO {
    private Long deviceId;
    private Values values;
}
