package com.example.demowebshop.pages;

import com.example.demowebshop.components.HeaderBar;
import com.example.demowebshop.components.HeaderMenuBar;
import com.example.demowebshop.config.AppConfig;
import com.example.demowebshop.ui.UserActionsUI;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BasePage<T extends BasePage<T>> {

    protected final WebDriver driver;
    protected final AppConfig config;
    protected final UserActionsUI ui;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.config = AppConfig.getInstance();        // singleton
        this.ui = new UserActionsUI(driver, Duration.ofSeconds(config.web().getExplicitWait()));
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public HeaderBar<T> header() {
        return new HeaderBar<>(driver, self());
    }

    public HeaderMenuBar menu() {
        return new HeaderMenuBar(driver, ui);
    }

}
