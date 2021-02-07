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
    private String email;
    private String encryptedPassword;
    private String resetPasswordToken;
    private Date resetPasswordSentAt;
    private Date rememberCreatedAt;
    private String role;
    private Date updatedAt;
    private Date createdAt;
}
