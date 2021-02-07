package com.stankin.machine.core.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String password;
}
