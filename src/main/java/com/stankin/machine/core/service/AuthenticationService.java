package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.InnerUser;
import com.stankin.machine.core.dto.UserAuthDTO;
import com.stankin.machine.core.service.domain.CustomUserDetailsService;
import com.stankin.machine.core.service.domain.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {

    private final EmployeeService employeeService;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthenticationService(EmployeeService employeeService,
                                 JWTUtil jwtUtil,
                                 CustomUserDetailsService customUserDetailsService) {
        this.employeeService = employeeService;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    public UserAuthDTO findUser(String jwt) {
        String userName = jwtUtil.extractUsername(jwt);
        if (userName == null) {
            throw new IllegalArgumentException("not found user!");
        }
        Optional<InnerUser> innerUserOptional = customUserDetailsService.findByEmail(userName);
        if (innerUserOptional.isPresent()) {
            UserAuthDTO userAuthDTO = new UserAuthDTO();
            InnerUser innerUser = innerUserOptional.get();
            userAuthDTO.setInnerUser(innerUser);
            Long employeeId = innerUser.getEmployeeId();
            Optional<Employee> employeeOptional = employeeService.findById(employeeId);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                userAuthDTO.setEmployee(employee);
            }
            return userAuthDTO;
        }
        throw new IllegalArgumentException("not found");
    }

}
