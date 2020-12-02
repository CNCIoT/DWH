package com.stankin.collector.domain.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stankin.collector.domain.Values;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@lombok.Data
@Table("values")
@Document(collation = "values")
public class Data {
    @Id
    @JsonIgnore
    private Long id;
    @Column("device_id")
    private Long deviceId;
    @Value("value")
    private Values value;
    @JsonIgnore
    @Column("created")
    private Date created = new Date();
}