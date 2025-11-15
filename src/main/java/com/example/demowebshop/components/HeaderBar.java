package com.example.demowebshop.components;

import com.example.demowebshop.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderBar<T> {

    private final WebDriver driver;
    private final T parentPage;

    // ===== HEADER LINKS =====
    private static final By REGISTER_LINK =
            By.cssSelector("a.ico-register");

    private static final By LOGIN_LINK =
            By.cssSelector("a.ico-login");

    private static final By LOGOUT_LINK =
            By.cssSelector("a.ico-logout");

    private static final By MY_ACCOUNT_LINK =
            By.cssSelector("a.ico-account");

    private static final By SHOPPING_CART_LINK =
            By.cssSelector("a.ico-cart");

    private static final By WISHLIST_LINK =
            By.cssSelector("a.ico-wishlist");

    private static final By HOME_LOGO_LINK =
            By.cssSelector("div.header-logo a");

    public HeaderBar(WebDriver driver, T parentPage) {
        this.driver = driver;
        this.parentPage = parentPage;
    }

    private WebElement el(By locator) {
        return driver.findElement(locator);
    }

    // ===== NAVIGATION METHODS (FLUENT, RETURNING NEW PAGE OBJECTS) =====

    public RegisterPage goToRegister() {
        el(REGISTER_LINK).click();
        return new RegisterPage(driver);
    }

    public LoginPage goToLogin() {
        el(LOGIN_LINK).click();
        return new LoginPage(driver);
    }

    public HomePage goToHome() {
        el(HOME_LOGO_LINK).click();
        return new HomePage(driver);
    }

    public ShoppingCartPage goToShoppingCart() {
        el(SHOPPING_CART_LINK).click();
        return new ShoppingCartPage(driver);
    }

    public WishlistPage goToWishlist() {
        el(WISHLIST_LINK).click();
        return new WishlistPage(driver);
    }

    public T logout() {
        el(LOGOUT_LINK).click();
        return parentPage;  // usually returns you to home page
    }

    // ===== BOOLEAN STATE HELPERS =====

    public boolean isLoggedIn() {
        return !driver.findElements(LOGOUT_LINK).isEmpty();
    }

    public boolean isLoggedOut() {
        return !driver.findElements(LOGIN_LINK).isEmpty();
    }
}
