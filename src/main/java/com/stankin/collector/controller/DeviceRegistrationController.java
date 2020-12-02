package com.stankin.collector.controller;

import com.stankin.collector.service.DeviceRegistrationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device/registration")
public class DeviceRegistrationController {
    
    private final DeviceRegistrationService deviceRegistrationService;

    public DeviceRegistrationController(DeviceRegistrationService deviceRegistrationService) {
        this.deviceRegistrationService = deviceRegistrationService;
    }
}
