package com.example.demowebshop.pages.orders;

import com.example.demowebshop.pages.BasePage;
import com.example.demowebshop.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage<CartPage> {

    private final By TERMS_OF_SERVICE = By.id("termsofservice");

    private final By CHECKOUT_BUTTON = By.id("checkout");
    // Title <h1>Shopping cart
    private final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    // Item rows in the shopping cart table
    private final By CART_ROWS =
            By.cssSelector("table.cart tbody tr");

    // "Update shopping cart" button
    private final By UPDATE_CART_BUTTON =
            By.name("updatecart");

    // "Continue shopping" button
    private final By CONTINUE_SHOPPING_BUTTON =
            By.name("continueshopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemsCount() {
        return driver.findElements(CART_ROWS).size();
    }

    public CartPage updateCart() {
        driver.findElement(UPDATE_CART_BUTTON).click();
        return self();
    }

    public HomePage continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new HomePage(driver);
    }

    public CartPage clickTermsOfService() {
        ui.waitUntilClickable(TERMS_OF_SERVICE).click();
        return self();
    }
    public CheckoutPage proceedToCheckout() {
        ui.waitUntilClickable(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }
}
