package com.stankin.collector.controller;

import com.stankin.collector.domain.table.Hub;
import com.stankin.collector.service.HubService;
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
import java.util.UUID;

@RestController
@RequestMapping("/hub")
public class HubController {

    private final HubService hubService;

    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    @Operation(summary = "Сохранение/обновление сущности Hub",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Hub.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    @PostMapping("/save")
    public ResponseEntity<Hub> save(@NotNull @RequestBody Hub hub) {
        return ResponseEntity.ok(hubService.save(hub));
    }

    @Operation(summary = "Получение всех сущностей Hub",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Hub.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    @GetMapping("/findAll")
    public ResponseEntity<List<Hub>> findAll() {
        return ResponseEntity.ok(hubService.findAll());
    }

    @Operation(summary = "Получение cущности Hub по id",
            parameters = {
                    @Parameter(name = "id", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Hub.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = RestUtil.NOT_FOUND,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    @GetMapping("/findById")
    public ResponseEntity<Hub> findById(@NotNull @RequestParam("id") Long id) {
        Optional<Hub> hubOptional = hubService.findById(id);
        return hubOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удаление сущности Device",
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY),
                    @ApiResponse(responseCode = RestUtil.ACCEPTED)
            })
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@NotNull @RequestBody Hub hub) {
        hubService.delete(hub);
        return ResponseEntity.accepted().build();
    }

}
