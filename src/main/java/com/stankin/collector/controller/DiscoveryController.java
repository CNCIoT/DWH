package com.stankin.collector.controller;

import com.stankin.collector.dto.discovery.DiscoveryHubDTO;
import com.stankin.collector.dto.discovery.ResponseRegHub;
import com.stankin.collector.service.DiscoveryService;
import com.stankin.collector.utils.RestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @Operation(summary = "Регистрация hub-a", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DiscoveryHubDTO.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/hub")
    public ResponseEntity<ResponseRegHub> registrationHub(@RequestBody DiscoveryHubDTO discoveryHubDTO) {
        return ResponseEntity.ok(discoveryService.registrationHub(discoveryHubDTO));
    }


}
