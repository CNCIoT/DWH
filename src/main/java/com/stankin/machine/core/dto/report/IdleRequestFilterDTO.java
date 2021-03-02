package com.stankin.machine.core.dto.report;

import lombok.Data;

import java.util.Date;

@Data
public class IdleRequestFilterDTO {
    private Date startDate;
    private Date endDate;
    private String scale;
    private String type;
}
