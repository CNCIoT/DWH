package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("plans")
public class Plan {
    @Id
    private Long id;
    private Date actualDate;
    private Long locationId;
    private Long techOperationId;
    private Integer amount;
    private Date updatedAt;
    private Date createdAt;
}
