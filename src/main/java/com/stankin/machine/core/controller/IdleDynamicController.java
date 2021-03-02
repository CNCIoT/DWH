package com.stankin.machine.core.controller;

import com.stankin.machine.core.dto.report.IdleRequestFilterDTO;
import com.stankin.machine.core.service.IdleDynamicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report/idle-dymamic")
public class IdleDynamicController {

    private IdleDynamicService idleDynamicService;

    @GetMapping
    public ResponseEntity<String> find(@RequestBody IdleRequestFilterDTO idleRequestFilterDTO){
        String result = idleDynamicService.find(idleRequestFilterDTO);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}
