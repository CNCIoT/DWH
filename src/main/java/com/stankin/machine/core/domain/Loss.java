package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("losses")
public class Loss {
    @Id
    private Long id;
    private Long lossTypeId;
    private Long lossKindId;
    private Long idleTypeId;
    private Long reasonIdleId;
    private Long factorLossId;
    private Long machineId;
    private Long locationId;
    private Date startAt;
    private Date endAt;
    private String lossPrimeCause;
    private Date createdAt;
    private Date updatedAt;
}
