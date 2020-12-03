package com.stankin.collector.controller;

import com.stankin.collector.dto.discovery.DiscoveryHubDTO;
import com.stankin.collector.service.DiscoveryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @Operation(summary = "Регистрация hub-а")
    @PostMapping("/hub")
    public ResponseEntity<Void> registrationHub(DiscoveryHubDTO discoveryHubDTO) {
        discoveryService.registrationHub(discoveryHubDTO);
        return ResponseEntity.accepted().build();
    }
}
