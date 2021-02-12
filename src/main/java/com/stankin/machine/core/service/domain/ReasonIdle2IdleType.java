package com.stankin.machine.core.service.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("reason_idle2idle_types")
public class ReasonIdle2IdleType {
    @Id
    private Long id;
    private Long reasonIdleId;
    private Long idleTypeId;
    private Date createdAt;
    private Date updatedAt;
}
