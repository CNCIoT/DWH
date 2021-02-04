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
    private String name;
    private String description;
    private String barcode;
    private Long locationId;
    private Date createdAt;
    private Date updatedAt;
    private Long machineModelId;
}
