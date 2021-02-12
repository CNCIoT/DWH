package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("reason_idles")
public class ReasonIdle {
    @Id
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
