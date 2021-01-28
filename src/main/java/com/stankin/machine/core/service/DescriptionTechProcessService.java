package com.stankin.machine.core.service;

import com.stankin.machine.core.dto.DescriptionTechProcessDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DescriptionTechProcessService {

    private final TechProcessService techProcessService;
    private final TechOperationService techOperationService;

    public DescriptionTechProcessService(TechProcessService techProcessService, TechOperationService techOperationService) {
        this.techProcessService = techProcessService;
        this.techOperationService = techOperationService;
    }

    public DescriptionTechProcessDTO save(DescriptionTechProcessDTO descriptionTechProcessDTO) {
        log.trace(">>save... descriptionTechProcessDTO={}", descriptionTechProcessDTO);

        return null;
    }

    public DescriptionTechProcessDTO findByTechProcessId(Long id) {
        return null;
    }
}
