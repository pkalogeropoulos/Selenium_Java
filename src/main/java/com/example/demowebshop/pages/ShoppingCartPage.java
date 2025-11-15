package com.example.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage<ShoppingCartPage> {

    // Title <h1>Shopping cart
    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    // Item rows in the shopping cart table
    private static final By CART_ROWS =
            By.cssSelector("table.cart tbody tr");

    // "Update shopping cart" button
    private static final By UPDATE_CART_BUTTON =
            By.name("updatecart");

    // "Continue shopping" button
    private static final By CONTINUE_SHOPPING_BUTTON =
            By.name("continueshopping");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemsCount() {
        return driver.findElements(CART_ROWS).size();
    }

    public ShoppingCartPage updateCart() {
        driver.findElement(UPDATE_CART_BUTTON).click();
        return self();
    }

    public HomePage continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new HomePage(driver);
    }
}
