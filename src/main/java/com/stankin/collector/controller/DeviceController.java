package com.stankin.collector.controller;

import com.stankin.collector.domain.table.Device;
import com.stankin.collector.service.DeviceService;
import com.stankin.collector.utils.RestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Operation(summary = "Сохранение/обновление сущности Device", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Device.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/save")
    public ResponseEntity<Device> save(@NotNull @RequestBody Device device) {
        return ResponseEntity.ok(deviceService.save(device));
    }

    @Operation(summary = "Получение всех сущностей Device", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Device.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<Device>> findAll() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @Operation(summary = "Получение cущности Device по id",
            parameters = {
                    @Parameter(name = "id", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Device.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = RestUtil.NOT_FOUND,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    @GetMapping("/findById")
    public ResponseEntity<Device> findById(@NotNull @RequestParam("id") Long id) {
        Optional<Device> deviceOptional = deviceService.findById(id);
        return deviceOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удаление сущности Device",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY),
                    @ApiResponse(responseCode = RestUtil.ACCEPTED)
            })
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@NotNull @RequestBody Device device) {
        deviceService.delete(device);
        return ResponseEntity.accepted().build();
    }


}
