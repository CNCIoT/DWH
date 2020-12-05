package com.stankin.collector.domain.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("devices")
public class Device {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("device_type_id")
    private Long deviceTypeId;
    @Column("created_at")
    private Date createdAt;
    @Column("updated_at")
    private Date updatedAt;
}
