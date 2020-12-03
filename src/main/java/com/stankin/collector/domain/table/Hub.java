package com.stankin.collector.domain.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("hubs")
public class Hub {
    @Id
    @Column("ID")
    private Long id;
    @Column("NAME")
    private String name;
    @Column("LOCATION")
    private String location;
    @Column("DESCRIPTION")
    private String description;
    @Column("V_VER")
    private String vVer;
    @Column("CREATED_AT")
    private Date createdAt;
    @Column("UPDATED_AT")
    private Date updatedAt;
}
