package com.stankin.collector.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Value {
    private String payload;
    private Date timestamp;
}
