package com.example.demowebshop.pages;

import com.example.demowebshop.pages.header.LoginPage;
import com.example.demowebshop.pages.header.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage<HomePage> {

    // ==========================
    // LOCATORS (UPPERCASE)
    // ==========================

    // --- Header / Auth ---
    private final By REGISTER_LINK_LOCATOR = By.linkText("Register");
    private final By LOGIN_LINK_LOCATOR = By.linkText("Log in");
    private final By SHOPPING_CART_LINK_LOCATOR = By.cssSelector("a[href='/cart']");
    private final By WISHLIST_LINK_LOCATOR = By.cssSelector("a[href='/wishlist']");
    private final By ACCOUNT_LINK_LOCATOR = By.cssSelector(".header-links .account");

    // --- Search ---
    private final By SEARCH_INPUT_LOCATOR = By.id("small-searchterms");
    private final By SEARCH_BUTTON_LOCATOR = By.cssSelector("input.button-1.search-box-button");

    // --- Top menu categories ---
    private final By BOOKS_CATEGORY_LOCATOR = By.linkText("Books");
    private final By COMPUTERS_CATEGORY_LOCATOR = By.linkText("Computers");
    private final By ELECTRONICS_CATEGORY_LOCATOR = By.linkText("Electronics");
    private final By APPAREL_CATEGORY_LOCATOR = By.linkText("Apparel & Shoes");
    private final By DIGITAL_DOWNLOADS_LOCATOR = By.linkText("Digital downloads");
    private final By JEWELRY_CATEGORY_LOCATOR = By.linkText("Jewelry");
    private final By GIFT_CARDS_LOCATOR = By.linkText("Gift Cards");

    // ==========================
    // CONSTRUCTOR
    // ==========================

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ==========================
    // ACTIONS
    // ==========================

    /**
     * Navigate to the Demo Web Shop homepage.
     */
    public HomePage open() {
        driver.get(config.web().getBaseUrl());   // Should be https://demowebshop.tricentis.com/
        return self();
    }

    // --- Search ---

    public HomePage typeSearch(String query) {
        ui.type(SEARCH_INPUT_LOCATOR, query);
        return self();
    }

    public SearchResultsPage clickSearchButton() {
        ui.waitUntilClickable(SEARCH_BUTTON_LOCATOR).click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage searchFor(String query) {
        return typeSearch(query).clickSearchButton();
    }

    // --- Authentication links ---

    public RegisterPage goToRegister() {
        ui.waitUntilClickable(REGISTER_LINK_LOCATOR).click();
        return new RegisterPage(driver);
    }

    public LoginPage goToLogin() {
        ui.waitUntilClickable(LOGIN_LINK_LOCATOR).click();
        return new LoginPage(driver);
    }

    // --- Cart / Wishlist ---

    public HomePage openShoppingCart() {
        ui.waitUntilClickable(SHOPPING_CART_LINK_LOCATOR).click();
        return self();
    }

    public HomePage openWishlist() {
        ui.waitUntilClickable(WISHLIST_LINK_LOCATOR).click();
        return self();
    }
}
 