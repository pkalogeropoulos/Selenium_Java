package com.example.demowebshop.config.enums;

import io.opentelemetry.api.internal.StringUtils;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    OPERA("opera");

    private final String name;

    Browser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser fromString(String env) {
        if (StringUtils.isNullOrEmpty(env)) {
            return CHROME;
        }
        env = env.trim().toLowerCase();
        for (Browser e : values()) {
            if (e.name.equalsIgnoreCase(env)) {
                return e;
            }
        }
        return CHROME; // fallback
    }
}
