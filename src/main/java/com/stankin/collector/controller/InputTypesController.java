package com.stankin.collector.controller;

import com.stankin.collector.domain.table.InputTypes;
import com.stankin.collector.service.InputTypeService;
import com.stankin.collector.utils.RestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/inputType")
public class InputTypesController {

    private final InputTypeService inputTypesService;

    public InputTypesController(InputTypeService inputTypesService) {
        this.inputTypesService = inputTypesService;
    }

    @GetMapping("/findAll")
    @Operation(summary = "Возвращает список всех InputTypes", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InputTypes.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Ошибка на сервере при попытке запросить данные")
    })
    public List<InputTypes> findAll() {
        return inputTypesService.findAll();
    }

    @GetMapping("/findKind")
    @Operation(summary = "Возвращает InputTypes по переданному kind", description = "",
            parameters = {
                    @Parameter(name = "kind", description = "тип параметра", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = RestUtil.HTTP_OK,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = InputTypes.class))),
                    @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Ошибка на сервере при попытке запросить данные")
            })
    public InputTypes findTitle(@NotNull @RequestParam("kind") String kind) {
        return inputTypesService.findByKind(kind);
    }
}