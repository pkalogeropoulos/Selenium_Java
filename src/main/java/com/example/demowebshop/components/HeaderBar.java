package com.example.demowebshop.components;

import com.example.demowebshop.pages.*;
import com.example.demowebshop.ui.UserActionsUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderBar<T> {

    private final WebDriver driver;
    private final T parentPage;
    private UserActionsUI ui;

    // ===== HEADER LINKS =====
    private static final By REGISTER_LINK =
            By.cssSelector("a.ico-register");

    private static final By LOGIN_LINK =
            By.cssSelector("a.ico-login");

    private static final By LOGOUT_LINK =
            By.cssSelector("a.ico-logout");

    private static final By MY_ACCOUNT_LINK =
            By.cssSelector("a.ico-account");

    private static final By CUSTOMER_INFO_ACCOUNT_LINK = By.cssSelector("li a.account");

    private static final By SHOPPING_CART_LINK =
            By.cssSelector("a.ico-cart");

    private static final By WISHLIST_LINK =
            By.cssSelector("a.ico-wishlist");

    private static final By HOME_LOGO_LINK =
            By.cssSelector("div.header-logo a");

    public HeaderBar(WebDriver driver, T parentPage) {
        this.driver = driver;
        this.parentPage = parentPage;
        ui = new UserActionsUI(driver);
    }

    // ===== NAVIGATION METHODS (FLUENT, RETURNING NEW PAGE OBJECTS) =====

    public RegisterPage goToRegister() {
        ui.waitUntilClickable(REGISTER_LINK).click();
        return new RegisterPage(driver);
    }

    public LoginPage goToLogin() {
        ui.waitUntilClickable(LOGIN_LINK).click();
        return new LoginPage(driver);
    }

    public HomePage goToHome() {
        ui.waitUntilClickable(HOME_LOGO_LINK).click();
        return new HomePage(driver);
    }

    public ShoppingCartPage goToShoppingCart() {
        ui.waitUntilClickable(SHOPPING_CART_LINK).click();
        return new ShoppingCartPage(driver);
    }

    public WishlistPage goToWishlist() {
        ui.waitUntilClickable(WISHLIST_LINK).click();
        return new WishlistPage(driver);
    }

    public T logout() {
        ui.waitUntilClickable(LOGOUT_LINK).click();
        return parentPage;  // usually returns you to home page
    }

    // ===== BOOLEAN AND STRING STATE HELPERS =====

    public boolean isLoggedIn() {
        return ui.isElementPresent(LOGOUT_LINK);
    }

    public boolean isLoggedOut() {
        return ui.isElementPresent(LOGIN_LINK);
    }

    public String getCustomerInfoAccountText() {
        return ui.waitUntilVisible(CUSTOMER_INFO_ACCOUNT_LINK).getText();
    }
}
