package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("loss_kinds")
public class LossKind {
    @Id
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
