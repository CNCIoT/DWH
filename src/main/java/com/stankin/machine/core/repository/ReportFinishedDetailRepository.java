package com.stankin.machine.core.repository;


import com.stankin.machine.core.dto.EmployeeReportFinishedDetailDTO;

import java.util.Date;
import java.util.List;

public interface ReportFinishedDetailRepository  {

    List<EmployeeReportFinishedDetailDTO> findAllEmployee(Long employeeId,
                                                          Date startDate,
                                                          Date endDate);


}
