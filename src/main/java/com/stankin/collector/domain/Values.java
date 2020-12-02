package com.stankin.collector.domain;

import lombok.Data;

import java.util.Map;


@Data
public class Values {
    private Map<String, Value> valueList;
}
