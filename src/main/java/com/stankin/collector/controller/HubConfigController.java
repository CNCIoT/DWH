package com.stankin.collector.controller;

import com.stankin.collector.service.HubConfigService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hub/conf")
public class HubConfigController {

    private final HubConfigService hubConfigService;

    public HubConfigController(HubConfigService hubConfigService) {
        this.hubConfigService = hubConfigService;
    }
}
