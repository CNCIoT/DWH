package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.InnerUser;
import com.stankin.machine.core.dto.AuthRequest;
import com.stankin.machine.core.dto.AuthResponse;
import com.stankin.machine.core.dto.UserAuthDTO;
import com.stankin.machine.core.service.AuthenticationService;
import com.stankin.machine.core.service.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtTokenUtil;

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTUtil jwtTokenUtil,
                                    AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getName(),
                    authRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwt);
        UserAuthDTO userAuthDTO = authenticationService.findUser(jwt);
        authResponse.setUserAuthDTO(userAuthDTO);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/authenticate/user")
    public ResponseEntity<UserAuthDTO> user(@RequestBody String jwt){
        UserAuthDTO userAuthDTO = authenticationService.findUser(jwt);
        return ResponseEntity.ok(userAuthDTO);
    }
}
