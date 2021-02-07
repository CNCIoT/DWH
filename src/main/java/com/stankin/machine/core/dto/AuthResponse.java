package com.stankin.machine.core.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwtToken;
    private UserAuthDTO userAuthDTO;
}
