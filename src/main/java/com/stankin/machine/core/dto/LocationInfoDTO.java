package com.stankin.machine.core.dto;

import com.stankin.machine.core.domain.Location;
import lombok.Data;

@Data
public class LocationInfoDTO {
    private Location location;
    private int countEmployee;
    private int countMachine;
}
