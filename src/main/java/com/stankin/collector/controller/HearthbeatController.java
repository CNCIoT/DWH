package com.stankin.collector.controller;

import com.stankin.collector.service.HearthbeatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
public class HearthbeatController {

    private final HearthbeatService hearthbeatService;

    public HearthbeatController(HearthbeatService hearthbeatService) {
        this.hearthbeatService = hearthbeatService;
    }
}
