package com.stankin.collector.controller;

import com.stankin.collector.domain.table.Hub2Device;
import com.stankin.collector.service.Hub2DeviceService;
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
@RequestMapping("/hub/devices/")
public class Hub2DeviceController {

    private final Hub2DeviceService hub2DeviceService;

    public Hub2DeviceController(Hub2DeviceService hub2DeviceService) {
        this.hub2DeviceService = hub2DeviceService;
    }

    @Operation(summary = "Сохранение/обновление сущности Hub2Device",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Hub2Device.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    @PostMapping("/save")
    public ResponseEntity<Hub2Device> save(@NotNull @RequestBody Hub2Device hub2Device) {
        return ResponseEntity.ok(hub2DeviceService.save(hub2Device));
    }

    @Operation(summary = "Получение всех сущностей Hub",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Hub2Device.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    @GetMapping("/findAll")
    public ResponseEntity<List<Hub2Device>> findAll() {
        return ResponseEntity.ok(hub2DeviceService.findAll());
    }

    @Operation(summary = "Получение cущности Hub2Device по id",
            parameters = {
                    @Parameter(name = "id", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Hub2Device.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = RestUtil.NOT_FOUND,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    @GetMapping("/findById")
    public ResponseEntity<Hub2Device> findById(@NotNull @RequestParam("id") Long id) {
        Optional<Hub2Device> hub2DeviceOptional = hub2DeviceService.findById(id);
        return hub2DeviceOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удаление сущности Hub2Device",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY),
                    @ApiResponse(responseCode = RestUtil.ACCEPTED)
            })
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@NotNull @RequestBody Hub2Device hub2Device) {
        hub2DeviceService.delete(hub2Device);
        return ResponseEntity.accepted().build();
    }


}
