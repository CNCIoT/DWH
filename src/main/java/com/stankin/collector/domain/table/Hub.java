package com.stankin.collector.domain.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Convert;
import java.util.Date;


@Data
@Table("hubs")
public class Hub {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("location")
    private String location;
    @Column("description")
    private String description;
    @Column("v_ver")
    private String vVer;
    @Column("device_list_available")
    private String deviceListAvailable;
    @Column("created_at")
    private Date createdAt;
    @Column("updated_at")
    private Date updatedAt;
}
