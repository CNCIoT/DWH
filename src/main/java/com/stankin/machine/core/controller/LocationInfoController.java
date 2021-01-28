package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.LocationInfoDTO;
import com.stankin.machine.core.service.LocationInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/location/info")
public class LocationInfoController {

    private final LocationInfoService locationInfoService;

    public LocationInfoController(LocationInfoService locationInfoService) {
        this.locationInfoService = locationInfoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationInfoDTO> findByLocationId(@NotNull @PathVariable("id") Long id) {
       LocationInfoDTO locationInfoDTO = locationInfoService.findByLocationId(id);
        return ResponseEntity.ok(locationInfoDTO);
    }
}
