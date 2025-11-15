package com.example.demowebshop.config;

import com.example.demowebshop.config.enums.Environment;

import java.io.IOException;
import java.util.Properties;

public final class AppConfig {

    private static AppConfig instance;

    private final Environment environment;
    private final WebConfig web;
    private final AuthConfig auth;
    private final ProductConfig productConfig;

    private AppConfig(Environment environment, Properties props) {
        this.environment = environment;
        this.web = new WebConfig(props);
        this.auth = new AuthConfig(props);
        this.productConfig = new ProductConfig(props);
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = loadInternal();
                }
            }
        }
        return instance;
    }

    private static AppConfig loadInternal() {
        String envName = System.getProperty("env");
        if (envName == null || envName.isBlank()) {
            envName = System.getenv("ENV");
        }

        Environment env = Environment.fromString(envName);
        String fileName = "config-" + env.getName() + ".properties";

        Properties props = new Properties();
        try (var in = AppConfig.class.getClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }
            props.load(in);
            return new AppConfig(env, props);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + fileName, e);
        }
    }

    public Environment getEnvironment() { return environment; }

    public WebConfig web() { return web; }

    public AuthConfig auth() { return auth; }

    public ProductConfig product() {
        return productConfig;
    }
}

