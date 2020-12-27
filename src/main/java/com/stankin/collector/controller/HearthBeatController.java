package com.stankin.collector.controller;

import com.stankin.collector.service.HearthBeatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
public class HearthBeatController {

    private final HearthBeatService hearthBeatService;

    public HearthBeatController(HearthBeatService hearthBeatService) {
        this.hearthBeatService = hearthBeatService;
    }
}
