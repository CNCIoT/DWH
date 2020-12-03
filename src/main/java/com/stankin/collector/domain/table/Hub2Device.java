package com.stankin.collector.domain.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("hub2device")
public class Hub2Device {
    @Id
    @Column("id")
    private Long id;
    @Column("device_id")
    private Long deviceId;
    @Column("created_at")
    private Date createdAt;
    @Column("updated_at")
    private Date updatedAt;
}
