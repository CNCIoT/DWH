package com.stankin.machine.core.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("users")
public class InnerUser {
    @Id
    private Long id;
    private String login;
    private String password;
    private Long employeeId;
    private String role;
    private Date updatedAt;
    private Date createdAt;
}
