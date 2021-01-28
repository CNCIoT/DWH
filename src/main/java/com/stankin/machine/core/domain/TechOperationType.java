package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("tech_operation_types")
@Data
public class TechOperationType {
    @Id
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
