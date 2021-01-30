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
    private String name;
    private String barcode;
    private String detailName;
    private Date createdAt;
    private Date updatedAt;
}
