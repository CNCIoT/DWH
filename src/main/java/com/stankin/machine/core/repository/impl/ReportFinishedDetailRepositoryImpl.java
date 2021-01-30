package com.stankin.machine.core.repository.impl;

import com.stankin.machine.core.dto.EmployeeReportFinishedDetailDTO;
import com.stankin.machine.core.repository.ReportFinishedDetailRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ReportFinishedDetailRepositoryImpl implements ReportFinishedDetailRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final static String ALL_EMPLOYEES = "SELECT e.employee_id, \n" +
            "               e.file_name,  \n" +
            "               COUNT(*) count_operation\n" +
            "            FROM mdc.executor_programs e\n" +
            "            WHERE (created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp)\n" +
            "                        AND created_at <= COALESCE(:end_date, now()))\n";

    public ReportFinishedDetailRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeReportFinishedDetailDTO> findAllEmployee(Long employeeId,
                                                                 Date startDate,
                                                                 Date endDate) {
        SqlParameterSource parameters = new MapSqlParameterSource("emp_id", employeeId)
                .addValue("start_date", startDate)
                .addValue("end_date", endDate);
        String request = ALL_EMPLOYEES;
        if(employeeId == null){
           request += " GROUP BY e.employee_id, file_name";
        }else{
            request += " AND employee_id = :emp_id\n" +
                    "            GROUP BY employee_id, file_name";
        }
        return jdbcTemplate.query(request, parameters, (rs, rowNum) -> {
            EmployeeReportFinishedDetailDTO employeeReportFinishedDetailDTO = new EmployeeReportFinishedDetailDTO();
            employeeReportFinishedDetailDTO.setEmployeeId(rs.getLong("employee_id"));
            employeeReportFinishedDetailDTO.setFileName(rs.getString("file_name"));
            employeeReportFinishedDetailDTO.setCountOperation(rs.getInt("count_operation"));
            return employeeReportFinishedDetailDTO;
        });
    }
}
