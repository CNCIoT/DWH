package com.stankin.collector.domain.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stankin.collector.utils.DateUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("values")
public class DataDTO {
    @Id
    private Long id;
    @Column("device_id")
    private Long deviceId;
    @Column("value")
    private String value;
    @JsonFormat(pattern = DateUtil.REST_DATE_FORMAT_STR)
    @Column("created")
    private Date created;
}
