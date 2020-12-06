package com.stankin.collector.controller;

import com.stankin.collector.domain.table.HubConfig;
import com.stankin.collector.service.HubConfigService;
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
@RequestMapping("/hub/conf")
public class HubConfigController {

    private final HubConfigService hubConfigService;

    public HubConfigController(HubConfigService hubConfigService) {
        this.hubConfigService = hubConfigService;
    }

    @Operation(summary = "Сохранение/обновление сущности HubConfig", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HubConfig.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/save")
    public ResponseEntity<HubConfig> save(@NotNull @RequestBody HubConfig hubConfig) {
        return ResponseEntity.ok(hubConfigService.save(hubConfig));
    }

    @Operation(summary = "Получение всех сущностей HubConfig", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HubConfig.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<HubConfig>> findAll() {
        return ResponseEntity.ok(hubConfigService.findAll());
    }


    @Operation(summary = "Получение cущности HubConfig по id",
            parameters = {
                    @Parameter(name = "id", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HubConfig.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = RestUtil.NOT_FOUND,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    @GetMapping("/findById")
    public ResponseEntity<HubConfig> findById(@NotNull @RequestParam Long id) {
        Optional<HubConfig> hubConfigOptional = hubConfigService.findById(id);
        return hubConfigOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удаление сущности HubConfig",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY),
                    @ApiResponse(responseCode = RestUtil.ACCEPTED)
            })
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@NotNull @RequestBody HubConfig hubConfig) {
        hubConfigService.delete(hubConfig);
        return ResponseEntity.accepted().build();
    }
}
