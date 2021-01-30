package com.stankin.machine.core.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("executor_programs")
@Data
public class ExecutorProgram {
    @Id
    private Long id;
    private Long employeeId;
    private Double actualMachineTime;
    private Date createdAt;
    private Date updatedAt;
    private String fileName;
    private String status;
}
