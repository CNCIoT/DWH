package com.stankin.machine.core.repository.impl;

import com.stankin.machine.core.domain.IdleDateReport;
import com.stankin.machine.core.dto.EmployeeReportFinishedDetailDTO;
import com.stankin.machine.core.repository.IdleDateReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class IdleDateReportRepositoryImpl implements IdleDateReportRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static String requestFindIdleTypeWithDate = "SELECT idle_type_id\n" +
            "  ,date_trunc('day', start_at) start_date\n" +
            "     ,SUM(DATE_PART('minute', end_at - start_at)) diff\n" +
            "           FROM mdc.losses\n" +
            "  WHERE idle_type_id = :idle_type_id AND (start_at >= :start_date" +
            "          AND end_at <= :end_date)\n" +
            "     GROUP BY idle_type_id, date_trunc('day', start_at) \n" +
            " ORDER BY date_trunc('day', start_at)";

    public IdleDateReportRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<IdleDateReport> findByIdleTypeWithDate(Long idleTypeId, Date startDate, Date endDate) {
        SqlParameterSource parameters = new MapSqlParameterSource("idle_type_id", idleTypeId)
                .addValue("start_date", startDate)
                .addValue("end_date", endDate);
        return jdbcTemplate.query(requestFindIdleTypeWithDate, parameters, (rs, rowNum) -> {
            IdleDateReport idleDateReport = new IdleDateReport();
            idleDateReport.setIdleTypeId(rs.getLong("idle_type_id"));
            idleDateReport.setStartDate(rs.getDate("start_date"));
            idleDateReport.setDiff(rs.getLong("diff"));
            return idleDateReport;
        });
    }
}
