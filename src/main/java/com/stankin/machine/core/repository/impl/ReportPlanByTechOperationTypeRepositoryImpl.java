package com.stankin.machine.core.repository.impl;

import com.stankin.machine.core.dto.report.ReportTechOperationTypeDTO;
import com.stankin.machine.core.repository.ReportPlanByTechOperationTypeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Date;
import java.util.List;

public class ReportPlanByTechOperationTypeRepositoryImpl implements ReportPlanByTechOperationTypeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String SQL = "SELECT src.name operation_name, src.fact, 100 plan, 100 implement\n" +
            "FROM (SELECT t2t.tech_process_id, tt_oper.name,\n" +
            " COUNT(t2t.tech_operation_id) / (SELECT src.c \n" +
            "\t\t\t\t\t\t\t\t FROM (SELECT t2t1.tech_process_id, tt_oper1.name, COUNT(t2t1.tech_operation_id) c\n" +
            "\t\t\t\tFROM mdc.tech_process2tech_operations t2t1\n" +
            "\t\t\t\tJOIN mdc.tech_operations  t_oper1 ON t_oper1.id = t2t1.tech_operation_id\n" +
            "\t\t\t\tJOIN mdc.tech_operation_types tt_oper1 ON tt_oper1.id = t_oper1.tech_operation_type_id\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t WHERE t2t1.tech_process_id = t2t.tech_process_id\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t AND tt_oper1.name = tt_oper.name\n" +
            "\t\t\t\tGROUP BY  t2t1.tech_process_id, tt_oper1.name) src ) fact\n" +
            " FROM mdc.executor_programs prog \n" +
            "JOIN mdc.tech_operations  t_oper ON t_oper.file_name_program = prog.file_name\n" +
            "JOIN mdc.tech_operation_types tt_oper ON tt_oper.id = t_oper.tech_operation_type_id\n" +
            "JOIN mdc.tech_process2tech_operations t2t ON t2t.tech_operation_id = t_oper.id \n" +
            "JOIN mdc.tech_processes proc ON proc.id = t2t.tech_process_id \n" +
            "WHERE (prog.created_at >= COALESCE (:start_date, '1970-01-01 01:00:00.0+00'::timestamp)\n" +
            "                                    AND prog.created_at <= COALESCE(:end_date, now()))\n" +
            "GROUP BY t2t.tech_process_id,  tt_oper.name)  src";

    public ReportPlanByTechOperationTypeRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ReportTechOperationTypeDTO> reportPlanByTechOperation(Date startDate, Date endDate) {
        SqlParameterSource parameters = new MapSqlParameterSource("start_date", startDate)
                .addValue("end_date", endDate);
        return jdbcTemplate.query(SQL, parameters, (rs, rowNum) -> {
            ReportTechOperationTypeDTO reportTechOperationTypeDTO = new ReportTechOperationTypeDTO();
            reportTechOperationTypeDTO.setTechOperationType(rs.getString("operation_name"));
            reportTechOperationTypeDTO.setFactAmount(rs.getLong("fact"));
            return reportTechOperationTypeDTO;
        });
    }
}
