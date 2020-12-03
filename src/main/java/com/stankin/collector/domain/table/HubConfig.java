package com.stankin.collector.domain.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("hub_configs")
public class HubConfig {
    @Id
    @Column("ID")
    private Long id;
    @Column("HUB_ID")
    private Long hubId;
    @Column("config")
    private String config;
    @Column("CREATED_AT")
    private Date createdAt;
    @Column("UPDATED_AT")
    private Date updatedAt;
}
