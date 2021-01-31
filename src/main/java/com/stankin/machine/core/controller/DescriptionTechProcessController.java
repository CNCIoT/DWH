package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.DescriptionTechProcessDTO;
import com.stankin.machine.core.service.domain.DescriptionTechProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/tech/process/description")
public class DescriptionTechProcessController {

    private final DescriptionTechProcessService descriptionTechProcessService;

    public DescriptionTechProcessController(DescriptionTechProcessService descriptionTechProcessService) {
        this.descriptionTechProcessService = descriptionTechProcessService;
    }

    @PostMapping
    public ResponseEntity<DescriptionTechProcessDTO> save(@NotNull
                                                          @RequestBody DescriptionTechProcessDTO descriptionTechProcessDTO){
        DescriptionTechProcessDTO newDescriptionTechProcessDTO =
                descriptionTechProcessService.save(descriptionTechProcessDTO);
        return ResponseEntity.ok(newDescriptionTechProcessDTO);
    }

    @GetMapping("/techprocess/{id}")
    public ResponseEntity<DescriptionTechProcessDTO> findByTechProcessId(@NotNull @PathVariable Long id){
        descriptionTechProcessService.findByTechProcessId(id);
        return ResponseEntity.ok(null);
    }
}
