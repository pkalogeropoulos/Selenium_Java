package com.example.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage extends BasePage<WishlistPage> {

    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    private static final By WISHLIST_ROWS =
            By.cssSelector("table.wishlist-content tbody tr");

    private static final By UPDATE_WISHLIST_BUTTON =
            By.name("updatecart");

    private static final By ADD_TO_CART_BUTTON =
            By.cssSelector("button[name='addtocartbutton']");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public int getWishlistItemsCount() {
        return driver.findElements(WISHLIST_ROWS).size();
    }

    public WishlistPage updateWishlist() {
        driver.findElement(UPDATE_WISHLIST_BUTTON).click();
        return self();
    }

    public ShoppingCartPage addToCart() {
        driver.findElement(ADD_TO_CART_BUTTON).click();
        return new ShoppingCartPage(driver);
    }
}
