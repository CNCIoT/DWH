package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.dto.report.IdleRequestFilterDTO;
import org.springframework.stereotype.Service;

@Service
public class IdleDynamicService {

    private final ReasonIdleService reasonIdleService;

    public IdleDynamicService(ReasonIdleService reasonIdleService) {
        this.reasonIdleService = reasonIdleService;
    }


    public String find(IdleRequestFilterDTO idleRequestFilterDTO) {

        return  null;
    }
}
