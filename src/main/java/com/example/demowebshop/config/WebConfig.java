package com.example.demowebshop.config;

import java.util.Properties;

public final class WebConfig {

    private final String baseUrl;
    private final String browser;
    private final int explicitWait;

    public WebConfig(Properties props) {
        this.baseUrl = required(props, "web.base.url");
        this.browser = required(props, "web.browser");
        this.explicitWait = Integer.parseInt(required(props, "web.explicit.wait"));
    }

    private static String required(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public int getExplicitWait() {return explicitWait;}
}
