package com.stankin.machine.core.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DateFilterDTO {
    private Date startDate;
    private Date endDate;
}
