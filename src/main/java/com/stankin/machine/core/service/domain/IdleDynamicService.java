package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.IdleDateReport;
import com.stankin.machine.core.domain.IdleType;
import com.stankin.machine.core.domain.Loss;
import com.stankin.machine.core.dto.report.IdleRequestFilterDTO;
import com.stankin.machine.core.dto.report.IdleResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IdleDynamicService {

    private final ReasonIdleService reasonIdleService;
    private final IdleTypeService idleTypeService;
    private final LossService lossService;
    private DateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");

    public IdleDynamicService(ReasonIdleService reasonIdleService,
                              IdleTypeService idleTypeService, LossService lossService) {
        this.reasonIdleService = reasonIdleService;
        this.idleTypeService = idleTypeService;
        this.lossService = lossService;
    }

    /**
     * @param idleRequestFilterDTO
     * @return
     */
    public IdleResponseDTO find(IdleRequestFilterDTO idleRequestFilterDTO) {
        log.trace(">>find... idleRequestFilterDTO={}", idleRequestFilterDTO);
        String types = idleRequestFilterDTO.getType();
        String[] splittedTypes = types.split(",");
        String type = splittedTypes[0];
        IdleType idleType = idleTypeService.findByKind(type);
        List<IdleDateReport> idleDateReports = lossService.findByIdleTypeWithDate(idleType.getId(), idleRequestFilterDTO.getStartDate(), idleRequestFilterDTO.getEndDate());
        IdleResponseDTO idleResponseDTO = new IdleResponseDTO();
        idleResponseDTO.idles = new String[idleDateReports.size()];
        for (int i = 0; i < idleDateReports.size(); i++) {
            IdleDateReport idleDateReport = idleDateReports.get(i);
            idleResponseDTO.idles[i] = "['" + idleDateReport.getStartDate() + "',"
                    + idleDateReport.getDiff() / 480 + ", 0" + "]";
        }
        return idleResponseDTO;
    }
}
