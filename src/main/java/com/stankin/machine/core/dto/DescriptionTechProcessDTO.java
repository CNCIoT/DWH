package com.stankin.machine.core.dto;

import com.stankin.machine.core.domain.TechOperation;
import com.stankin.machine.core.domain.TechProcess;
import lombok.Data;

import java.util.List;

@Data
public class DescriptionTechProcessDTO {
    private TechProcess techProcess;
    private List<TechOperation> techOperationList;
}
