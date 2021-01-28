package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Location;
import com.stankin.machine.core.dto.LocationInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class LocationInfoService {

    private final LocationService locationService;
    private final EmployeeService employeeService;
    private final MachineService machineService;

    public LocationInfoService(LocationService locationService,
                               EmployeeService employeeService,
                               MachineService machineService) {
        this.locationService = locationService;
        this.employeeService = employeeService;
        this.machineService = machineService;
    }

    public LocationInfoDTO findByLocationId(@NotNull Long id) {
        log.trace(">>findByLocationId... id={}", id);

        Optional<Location> locationOptional = locationService.findById(id);
        if (locationOptional.isPresent()) {
            LocationInfoDTO locationInfoDTO = new LocationInfoDTO();
            locationInfoDTO.setLocation(locationOptional.get());
            int countEmp = employeeService.findCountEmpByLocationId(id);
            locationInfoDTO.setCountEmployee(countEmp);
            int countMachine = machineService.findCountMachineLocationId(id);
            locationInfoDTO.setCountMachine(countMachine);
            return locationInfoDTO;
        } else {
            throw new IllegalStateException("not found");
        }

    }
}
