package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("tech_processes")
public class TechProcess {
    @Id
    private Long id;
    private String barcode;
    private Date createdAt;
    private Date updatedAt;
}
