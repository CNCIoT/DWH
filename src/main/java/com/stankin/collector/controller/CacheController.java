package com.stankin.collector.controller;

import com.stankin.collector.service.CacheService;
import com.stankin.collector.utils.RestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheService cacheService;

    @Autowired
    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/clear")
    @Operation(summary = "сброс кэша", description = "", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY)
    })
    public ResponseEntity<Void> clear() {
        cacheService.clear();
        return ResponseEntity.accepted().build();
    }

}
