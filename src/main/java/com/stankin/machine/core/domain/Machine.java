package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;


@Table("machines")
@Data
public class Machine {
    @Id
    private Long id;
    private String barcode;
    private Long machineId;
    private boolean isDefect;
    private Date createdAt;
    private Date updatedAt;
}
