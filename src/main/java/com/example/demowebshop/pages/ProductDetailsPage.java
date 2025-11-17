package com.example.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class ProductDetailsPage extends BasePage<ProductDetailsPage> {

    // ==========================
    // LOCATORS
    // ==========================

    // Main product info
    private final By PRODUCT_TITLE_LOCATOR = By.cssSelector("div.product-name h1");
    private final By SHORT_DESCRIPTION_LOCATOR = By.cssSelector("div.short-description");
    private final By FULL_DESCRIPTION_LOCATOR = By.cssSelector("div.full-description");

    // SKU & availability (optional but useful)
    private final By SKU_VALUE_LOCATOR = By.cssSelector("div.sku span.value");
    private final By AVAILABILITY_VALUE_LOCATOR = By.cssSelector("div.stock span.value");

    // Price
    // Demo Web Shop usually uses span with class like "price-value-XX"
    private final By PRODUCT_PRICE_LOCATOR = By.cssSelector("div.product-price span[class^='price-value-']");

    // Quantity + Add to cart
    private final By QUANTITY_INPUT_LOCATOR = By.cssSelector("input.qty-input");
    private final By ADD_TO_CART_BUTTON_LOCATOR = By.cssSelector("input.button-1.add-to-cart-button");

    // Wishlist / compare / email a friend
    private final By ADD_TO_WISHLIST_BUTTON_LOCATOR = By.cssSelector("input.button-2.add-to-wishlist-button");
    private final By ADD_TO_COMPARE_BUTTON_LOCATOR = By.cssSelector("input.button-2.add-to-compare-list-button");
    private final By EMAIL_A_FRIEND_BUTTON_LOCATOR = By.cssSelector("input.button-2.email-a-friend-button");
    private final By NOTIFICATION_BAR_LOCATOR = By.id("bar-notification");

    // Review links
    private final By ADD_REVIEW_LINK_LOCATOR = By.cssSelector("div.product-review-links a[href*='productreviews']");

    // ==========================
    // CONSTRUCTOR
    // ==========================

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    // ==========================
    // GETTERS / READ METHODS
    // ==========================

    public String getProductTitle() {
        return ui.waitUntilVisible(PRODUCT_TITLE_LOCATOR).getText();
    }

    public String getShortDescription() {
        WebElement el = ui.waitUntilVisible(SHORT_DESCRIPTION_LOCATOR);
        return el.getText();
    }

    public String getFullDescription() {
        WebElement el = ui.waitUntilVisible(FULL_DESCRIPTION_LOCATOR);
        return el.getText();
    }

    public String getSku() {
        if (driver.findElements(SKU_VALUE_LOCATOR).isEmpty()) {
            return null;
        }
        return driver.findElement(SKU_VALUE_LOCATOR).getText();
    }

    public String getAvailabilityText() {
        if (driver.findElements(AVAILABILITY_VALUE_LOCATOR).isEmpty()) {
            return null;
        }
        return driver.findElement(AVAILABILITY_VALUE_LOCATOR).getText();
    }

    public boolean isInStock() {
        String availability = getAvailabilityText();
        return availability != null && availability.toLowerCase().contains("in stock");
    }

    /**
     * Returns the raw price text, e.g. "$24.00".
     */
    public String getPriceText() {
        return ui.waitUntilVisible(PRODUCT_PRICE_LOCATOR).getText();
    }

    /**
     * Returns the price as BigDecimal (strips currency symbol).
     */
    public BigDecimal getPrice() {
        String text = getPriceText(); // e.g. "$24.00"
        String digits = text.replaceAll("[^0-9.,]", ""); // "24.00"
        // Normalize comma vs dot if needed; for now assume dot decimal.
        return new BigDecimal(digits.replace(",", ""));
    }

    public int getQuantity() {
        WebElement qtyInput = ui.waitUntilVisible(QUANTITY_INPUT_LOCATOR);
        String value = qtyInput.getAttribute("value");
        return (value == null || value.isBlank()) ? 0 : Integer.parseInt(value);
    }

    // ==========================
    // ACTIONS
    // ==========================

    public ProductDetailsPage setQuantity(int quantity) {
        WebElement qtyInput = ui.waitUntilVisible(QUANTITY_INPUT_LOCATOR);
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(quantity));
        return self();
    }

    public ProductDetailsPage addToCart() {
        ui.waitUntilClickable(ADD_TO_CART_BUTTON_LOCATOR).click();
        // Demo Web Shop typically stays on the same page and shows a notification bar.
        return self();
    }

    public ProductDetailsPage addToCart(int quantity) {
        return setQuantity(quantity).addToCart();
    }

    public ProductDetailsPage addToWishlist() {
        ui.waitUntilClickable(ADD_TO_WISHLIST_BUTTON_LOCATOR).click();
        return self();
    }

    public ProductDetailsPage addToCompareList() {
        ui.waitUntilClickable(ADD_TO_COMPARE_BUTTON_LOCATOR).click();
        return self();
    }

    public ProductDetailsPage emailAFriend() {
        ui.waitUntilClickable(EMAIL_A_FRIEND_BUTTON_LOCATOR).click();
        // In reality this navigates to an "Email a friend" page,
        // but you can change the return type later if you model that page.
        return self();
    }

    public boolean isNotificationBarVisible() {
        return ui.isElementPresent(NOTIFICATION_BAR_LOCATOR);
    }
}
