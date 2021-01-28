package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.Location;
import com.stankin.machine.core.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> save(@NotNull @RequestBody Location location) {
        Location newLocation = locationService.save(location);
        return ResponseEntity.ok(newLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> findById(@NotNull @PathVariable("id") Long id) {
        Optional<Location> locationOptional = locationService.findById(id);
        return locationOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody Location location){
        locationService.delete(location);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Location>> findAll(){
        return ResponseEntity.ok(locationService.findAll());
    }

}
