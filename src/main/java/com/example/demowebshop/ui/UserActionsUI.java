package com.example.demowebshop.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
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

    public WebElement waitUntilClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public boolean isElementPresent(By locator) {
        try {
            waitUntilVisible(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void click(By locator) {
        waitUntilClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement el = waitUntilVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public void hover(By locator) {
        new Actions(driver)
                .moveToElement(waitUntilClickable(locator))
                .perform();
    }

    public void scrollTo(By locator) {
        this.scrollTo(waitUntilVisible(locator));
    }

    public void scrollTo(WebElement element) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
                        element);
    }

    public void hover(WebElement element) {
        new Actions(driver)
                .moveToElement(waitUntilClickable(element))
                .perform();
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
