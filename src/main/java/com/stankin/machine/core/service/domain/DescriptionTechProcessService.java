package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.TechProcess;
import com.stankin.machine.core.dto.DescriptionTechProcessDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<TechProcess>  techProcessOptional = techProcessService.findById(id);
        if(techProcessOptional.isPresent()){
            DescriptionTechProcessDTO descriptionTechProcessDTO = new DescriptionTechProcessDTO();
            TechProcess techProcess = techProcessOptional.get();
            descriptionTechProcessDTO.setTechProcess(techProcess);

        }
        return null;
    }
}
