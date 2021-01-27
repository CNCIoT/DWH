package com.stankin.machine.core.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("locations")
public class Location {
    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
