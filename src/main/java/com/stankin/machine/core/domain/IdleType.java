package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("idle_types")
public class IdleType {
    @Id
    private Long id;
    private String name;
    private String code;
    private Date createdAt;
    private Date updatedAt;
}
