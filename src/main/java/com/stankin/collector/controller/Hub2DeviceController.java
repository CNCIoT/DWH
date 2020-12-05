package com.stankin.collector.controller;

import com.stankin.collector.service.Hub2DeviceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hub/devices/")
public class Hub2DeviceController {

    private final Hub2DeviceService hub2DeviceService;


    public Hub2DeviceController(Hub2DeviceService hub2DeviceService) {
        this.hub2DeviceService = hub2DeviceService;
    }
}
