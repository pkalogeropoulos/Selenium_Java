package com.example.demowebshop.config.enums;

import io.opentelemetry.api.internal.StringUtils;

public enum Environment {
    DEV("dev"),
    QA("qa"),
    STAGING("staging"),
    PRODUCTION("production");


    private final String name;

    Environment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Environment fromString(String env) {
        if (StringUtils.isNullOrEmpty(env)) {
            return DEV;
        }
        env = env.trim().toLowerCase();
        for (Environment e : values()) {
            if (e.name.equalsIgnoreCase(env)) {
                return e;
            }
        }
        return PRODUCTION; // fallback
    }
}