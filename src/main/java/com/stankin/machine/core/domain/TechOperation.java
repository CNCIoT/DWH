package com.stankin.machine.core.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("tech_operations")
public class TechOperation {
    @Id
    private Long id;
    private String name;
    private Long techOperationTypeId;
    private double scheduled_machine_time;
    private Date createdAt;
    private Date updatedAt;
    private Long machineId;
    private int rownum;
    private String fileNameProgram;
}
