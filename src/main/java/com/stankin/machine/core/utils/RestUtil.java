package com.stankin.machine.core.utils;

public class RestUtil {
    public static final String HTTP_OK = "200";
    public static final String BAD_GATEWAY = "500";
    public static final String ACCEPTED = "202";
    public static final String NOT_FOUND = "404";

    private RestUtil() {
        throw new IllegalStateException("Utility class");
    }
}
