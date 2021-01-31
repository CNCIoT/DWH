package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.Location;
import com.stankin.machine.core.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location save(@NotNull Location location) {
        log.trace(">>save.... location={}", location);
        Optional<Location> optionalLocation = locationRepository.findById(location.getId());
        if (optionalLocation.isPresent()) {
            log.trace("update existing location");
            return locationRepository.save(location);
        }
        log.trace("create new location");
        location.setId(null);
        return locationRepository.save(location);
    }

    public Optional<Location> findById(@NotNull Long id) {
        log.trace(">>findById... id={}", id);
        return locationRepository.findById(id);
    }

    public void delete(@NotNull Location location) {
        log.trace(">>delete... location={}", location);
        locationRepository.delete(location);
    }

    public List<Location> findAll() {
        log.trace(">>findAll...");
        return locationRepository.findAll();
    }
}
