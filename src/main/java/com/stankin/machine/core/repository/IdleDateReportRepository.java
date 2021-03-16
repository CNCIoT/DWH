package com.stankin.machine.core.repository;

import com.stankin.machine.core.domain.IdleDateReport;

import java.util.Date;
import java.util.List;

public interface IdleDateReportRepository {
    List<IdleDateReport> findByIdleTypeWithDate(Long idleTypeId,
                                                Date startDate,
                                                Date endDate);
}
