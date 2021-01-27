package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.dto.DescriptionTechProcessDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DescriptionTechProcessService {

    private final TechProcessService techProcessService;
    private final TechOperation techOperation;

    public DescriptionTechProcessService(TechProcessService techProcessService,
                                         TechOperation techOperation) {
        this.techProcessService = techProcessService;
        this.techOperation = techOperation;
    }

    public DescriptionTechProcessDTO save(DescriptionTechProcessDTO descriptionTechProcessDTO) {
        log.trace(">>save... descriptionTechProcessDTO={}", descriptionTechProcessDTO);

        return null;
    }

    public DescriptionTechProcessDTO findByTechProcessId(Long id) {
        return null;
    }
}
