package com.stankin.collector.dto.discovery;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseRegHub {
    private Long hubId;
    private Map<Long, String> deviceIds;
}
