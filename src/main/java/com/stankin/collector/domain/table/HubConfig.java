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
    @Column("id")
    private Long id;
    @Column("hub_id")
    private Long hubId;
    @Column("config")
    private String config;
    @Column("created_at")
    private Date createdAt;
    @Column("updated_at")
    private Date updatedAt;
    @Column("hash")
    private String hash;
}
