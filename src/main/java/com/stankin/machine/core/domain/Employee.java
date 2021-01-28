package com.stankin.machine.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("employees")
@Data
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date createdAt;
    private Date updatedAt;
    private Long positionId;
    private Long locationId;
}
