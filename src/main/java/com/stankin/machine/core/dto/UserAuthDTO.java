package com.stankin.machine.core.dto;

import com.stankin.machine.core.domain.Employee;
import com.stankin.machine.core.domain.InnerUser;
import lombok.Data;

@Data
public class UserAuthDTO {
    private InnerUser innerUser;
    private Employee employee;
}
