package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.report.IdleRequestFilterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report/idle-prime-cause")
public class IdlePrimeCauseController {

    private final IdlePrimeCauseService idlePrimeCauseService;

    public IdlePrimeCauseController(IdlePrimeCauseService idlePrimeCauseService) {
        this.idlePrimeCauseService = idlePrimeCauseService;
    }

    @GetMapping
    public ResponseEntity<String> find(@RequestBody IdleRequestFilterDTO filter){
        String result = idlePrimeCauseService.find(filter);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
