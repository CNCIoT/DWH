package com.stankin.collector.domain.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("input_types")
public class InputTypes {
    @Id
    private Long id;
    @Column("title")
    private String title;
    @Column("description")
    private String description;
    @Column("kind")
    private String kind;
    @Column("created_at")
    private Date createdAt;
    @Column("updated_at")
    private Date updatedAt;
    @Column("unit")
    private String unit;
}
