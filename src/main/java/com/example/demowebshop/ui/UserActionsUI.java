package com.example.demowebshop.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UserActionsUI {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public UserActionsUI(WebDriver driver) {
        this(driver, Duration.ofSeconds(10));
    }

    public UserActionsUI(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitUntilVisibleAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void click(By locator) {
        waitUntilClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement el = waitUntilVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By locator) {
        return waitUntilVisible(locator).getText();
    }

    public void scrollIntoView(By locator) {
        WebElement el = waitUntilVisible(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", el);
    }
}
