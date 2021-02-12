package com.stankin.machine.core.service.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("loss_type2loss_kinds")
public class LossType2LossKind {
    @Id
    private Long id;
    private Long lossKindId;
    private Long lossTypeId;
    private Date createdAt;
    private Date updatedAt;
}
