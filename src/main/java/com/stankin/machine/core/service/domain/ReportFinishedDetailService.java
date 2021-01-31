package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.dto.EmployeeReportFinishedDetailDTO;
import com.stankin.machine.core.repository.ReportFinishedDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ReportFinishedDetailService {

    private final ReportFinishedDetailRepository reportFinishedDetailRepository;

    public ReportFinishedDetailService(ReportFinishedDetailRepository reportFinishedDetailRepository) {
        this.reportFinishedDetailRepository = reportFinishedDetailRepository;
    }


    public List<EmployeeReportFinishedDetailDTO> findAllEmployee(Long employeeId,
                                                                 Date startDate,
                                                                 Date endDate){
        log.trace(">>findAllEmployee... startDate={}, endDate={}", startDate, endDate);
        return reportFinishedDetailRepository.findAllEmployee(employeeId, startDate, endDate);
    }


}
