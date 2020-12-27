package com.stankin.collector.service;

import com.google.gson.Gson;
import com.stankin.collector.converter.DeviceHubDTOToDeviceConverter;
import com.stankin.collector.domain.table.Device;
import com.stankin.collector.domain.table.Hub;
import com.stankin.collector.domain.table.Hub2Device;
import com.stankin.collector.domain.table.InputTypes;
import com.stankin.collector.dto.discovery.DeviceHubDTO;
import com.stankin.collector.dto.discovery.DiscoveryHubDTO;
import com.stankin.collector.dto.discovery.ResponseRegHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

@Service
@Slf4j
public class DiscoveryService {

    private final DeviceService deviceService;
    private final HubService hubService;
    private final DeviceHubDTOToDeviceConverter deviceHubDTOToDeviceConverter;
    private final Hub2DeviceService hub2DeviceService;
    private final InputTypeService inputTypeService;

    public DiscoveryService(DeviceService deviceService,
                            HubService hubService,
                            DeviceHubDTOToDeviceConverter deviceHubDTOToDeviceConverter,
                            Hub2DeviceService hub2DeviceService,
                            InputTypeService inputTypeService) {
        this.deviceService = deviceService;
        this.hubService = hubService;
        this.deviceHubDTOToDeviceConverter = deviceHubDTOToDeviceConverter;
        this.hub2DeviceService = hub2DeviceService;
        this.inputTypeService = inputTypeService;
    }

    @Transactional
    public ResponseRegHub registrationHub(DiscoveryHubDTO discoveryHubDTO) {
        log.trace("Entering method registrationHub... entity={}", discoveryHubDTO);
        ResponseRegHub responseRegHub = new ResponseRegHub();
        Hub newHub = hubService.save(hubService.mockHub(new Gson().toJson(discoveryHubDTO.getDeviceList())));
        responseRegHub.setHubId(newHub.getId());
        List<DeviceHubDTO> deviceList = discoveryHubDTO.getDeviceList();
        Map<Long, String> deviceMap = setUpDeviceHub(newHub, discoveryHubDTO.getDeviceList());
        hubService.updateDeviceListAvailable(newHub.getId(), new Gson().toJson(deviceList));
        responseRegHub.setDeviceIds(deviceMap);
        return responseRegHub;
    }

    private Map<Long, String> setUpDeviceHub(Hub hub, List<DeviceHubDTO> deviceHubDTOList) {
        log.trace(">>setUpDeviceHub... deviceHubDTOList={}", deviceHubDTOList);
        Map<Long, String> deviceMap = new HashMap<>();
        for (DeviceHubDTO deviceHubDTO : deviceHubDTOList) {
            checkInputTypes(deviceHubDTO.getInputTypes());
            Device device = deviceService.save(deviceHubDTOToDeviceConverter.convert(deviceHubDTO));
            deviceHubDTO.setDeviceId(device.getId());
            deviceMap.put(device.getId(), device.getName());
            log.trace("create new device... device={}", device);
            Hub2Device hub2Device = createEntityHub2Device(hub, device);
            Hub2Device newHub2Device = hub2DeviceService.save(hub2Device);
            log.trace("create newHub2Device... entity={}", newHub2Device);
        }
        log.trace("<<setUpDeviceHub... deviceMap={}", deviceMap);
        return deviceMap;
    }

    /**
     * Служебный метол проверки на сущнствование параметров в inputType
     */
    private void checkInputTypes(String[] inputTypes) {
        log.trace(">>checkInputTypes... inputTypes={}", inputTypes);
        for (String inputType : inputTypes) {
            InputTypes foundInputTypes = inputTypeService.findByKind(inputType);
            if (foundInputTypes == null) {
                String msg = String.format("Параметр %s не найден в системе", inputType);
                log.error(msg);
                throw new IllegalArgumentException(msg);
            }
        }
        log.trace("<<checkInputTypes...");
    }

    private Hub2Device createEntityHub2Device(@NotNull Hub hub, @NotNull Device device) {
        log.trace("Entering method createEntityHub2Device... hub={}, device={}", hub, device);
        Hub2Device hub2Device = new Hub2Device();
        hub2Device.setId(-1L);
        hub2Device.setHubId(hub.getId());
        hub2Device.setDeviceId(device.getId());
        hub2Device.setCreatedAt(new Date());
        hub2Device.setUpdatedAt(new Date());
        return hub2Device;
    }
}
