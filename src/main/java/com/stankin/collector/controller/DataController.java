package com.stankin.collector.controller;

import com.stankin.collector.domain.table.Data;
import com.stankin.collector.service.DataService;
import com.stankin.collector.utils.RestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @Operation(summary = "сохранение данных в хранилище", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Data.class)),
                    description = "Валидация и создания данных в таблицу values"),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Ошибка на сервере при сохранении данных")
    })
    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(@NotNull @RequestBody Data data) {
        dataService.save(data);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Метод возвращает заданное количество строк с данными по переданному device_id",
            parameters = {
                    @Parameter(name = "deviceId", description = "идентификатор устройства", required = true),
                    @Parameter(name = "rows", description = "количество строк вывода", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Data.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            })
    @GetMapping(value = "/findByDeviceIdWithRows")
    public ResponseEntity<List<Data>> findByDeviceIdWithRows(@NotNull @RequestParam("deviceId") Long id,
                                                             @NotNull @RequestParam("rows") Long rows) {
        return ResponseEntity.ok(dataService.findByDeviceIdWithRows(id, rows));
    }
}
