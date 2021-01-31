package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("tech_process2tech_operations")
public class TechProcess2TechOperation {
    @Id
    private Long id;
    private Long techProcessId;
    private Long techOperationId;
    private Date createdAt;
    private Date updatedAt;
}
