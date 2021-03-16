package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.report.IdleRequestFilterDTO;
import com.stankin.machine.core.dto.report.IdleResponseDTO;
import com.stankin.machine.core.service.domain.IdleDynamicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report/idle-dynamic")
public class IdleDynamicController {

    private final IdleDynamicService idleDynamicService;

    public IdleDynamicController(IdleDynamicService idleDynamicService) {
        this.idleDynamicService = idleDynamicService;
    }

    @PostMapping
    public ResponseEntity<IdleResponseDTO> find(@RequestBody IdleRequestFilterDTO idleRequestFilterDTO){
        IdleResponseDTO result = idleDynamicService.find(idleRequestFilterDTO);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }




}
