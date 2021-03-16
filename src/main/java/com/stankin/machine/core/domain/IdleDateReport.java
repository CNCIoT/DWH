package com.stankin.machine.core.domain;

import lombok.Data;

import java.util.Date;

@Data
public class IdleDateReport {
    Long idleTypeId;
    Date startDate;
    Long diff;
}
