package com.stankin.machine.core.repository;

import com.stankin.machine.core.dto.report.ReportTechOperationTypeDTO;

import java.util.Date;
import java.util.List;

public interface ReportPlanByTechOperationTypeRepository {
    List<ReportTechOperationTypeDTO> reportPlanByTechOperation(Date startDate, Date endDate);
}
