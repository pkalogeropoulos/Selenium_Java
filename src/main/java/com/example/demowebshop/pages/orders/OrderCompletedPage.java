package com.example.demowebshop.pages.orders;

import com.example.demowebshop.pages.BasePage;
import com.example.demowebshop.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderCompletedPage extends BasePage<OrderCompletedPage> {

    // ===== LOCATORS =====

    // Top title: usually "Thank you"
    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    // Main message: "Your order has been successfully processed!"
    private static final By ORDER_COMPLETED_MESSAGE =
            By.cssSelector("div.section.order-completed div.title strong");

    // Order number, e.g. "Order #12345"
    private static final By ORDER_NUMBER =
            By.cssSelector("ul.details li:first-child");

    // "Continue" button that typically returns to home page
    private static final By CONTINUE_BUTTON =
            By.cssSelector("div.buttons input.button-2.order-completed-continue-button");

    public OrderCompletedPage(WebDriver driver) {
        super(driver);
    }

    // ===== GETTERS =====

    /**
     * Returns the page title text, typically "Thank you".
     */
    public String getPageTitle() {
        return ui.waitUntilVisible(PAGE_TITLE).getText();
    }

    /**
     * Returns the main completion message,
     * e.g. "Your order has been successfully processed!".
     */
    public String getOrderCompletedMessage() {
        return ui.waitUntilVisible(ORDER_COMPLETED_MESSAGE).getText();
    }

    /**
     * Returns the full order number text,
     * e.g. "Order #12345".
     */
    public String getOrderNumberFullText() {
        return ui.waitUntilVisible(ORDER_NUMBER).getText();
    }

    /**
     * Returns just the numeric part of the order number, if present.
     * e.g. from "Order #12345" -> "12345".
     */
    public String getOrderNumber() {
        String full = getOrderNumberFullText();
        // naive parse: strip non-digits
        return full.replaceAll("\\D+", "");
    }

    // ===== ACTIONS =====

    /**
     * Clicks the Continue button and returns to the HomePage.
     */
    public HomePage clickContinue() {
        ui.waitUntilClickable(CONTINUE_BUTTON).click();
        return new HomePage(driver);
    }

    // ===== BOOLEAN HELPERS =====

    public boolean isOrderCompletedMessageDisplayed() {
        return !driver.findElements(ORDER_COMPLETED_MESSAGE).isEmpty();
    }

    public boolean isOrderNumberDisplayed() {
        return !driver.findElements(ORDER_NUMBER).isEmpty();
    }
}
