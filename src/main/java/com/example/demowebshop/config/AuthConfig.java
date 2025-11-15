package com.example.demowebshop.config;

import java.util.Properties;

public final class AuthConfig {

    private final String defaultUsername;
    private final String defaultPassword;

    public AuthConfig(Properties props) {
        this.defaultUsername = props.getProperty("auth.default.username");
        this.defaultPassword = props.getProperty("auth.default.password");
    }

    public String getDefaultUsername() { return defaultUsername; }
    public String getDefaultPassword() { return defaultPassword; }
}