package com.example.demowebshop.pages.header;

import com.example.demowebshop.pages.BasePage;
import com.example.demowebshop.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage<LoginPage> {

    // ===== PAGE ELEMENTS =====

    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    private static final By EMAIL_INPUT =
            By.id("Email");

    private static final By PASSWORD_INPUT =
            By.id("Password");

    private static final By REMEMBER_ME_CHECKBOX =
            By.id("RememberMe");

    private static final By LOGIN_BUTTON =
            By.cssSelector("input.button-1.login-button");

    private static final By LOGIN_ERROR_MESSAGE =
            By.cssSelector("div.validation-summary-errors li"); // appears on invalid login

    private static final By EMAIL_FIELD_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='Email']");

    private static final By PASSWORD_FIELD_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='Password']");

    // ==========================
    // VALIDATION ERRORS LOCATORS
    // ==========================
    private static final By VALIDATION_SUMMARY_ERRORS_LOCATORS = By.cssSelector("div.validation-summary-errors span");
    private static final By LOGIN_VALIDATION_ERROR_LOCATOR = By.cssSelector("div.validation-summary-errors ul li");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ===== INPUT METHODS =====

    public LoginPage setEmail(String email) {
        WebElement emailElement = ui.waitUntilVisible(EMAIL_INPUT);
        emailElement.clear();
        emailElement.sendKeys(email);
        return self();
    }

    public LoginPage setPassword(String password) {
        WebElement passwordElement = ui.waitUntilVisible(PASSWORD_INPUT);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return self();
    }

    public HomePage clickLoginButton() {
        ui.waitUntilClickable(LOGIN_BUTTON).click();
        return new HomePage(driver);
    }

    public HomePage performLogin(String userEmail, String password) {
        return this.setEmail(userEmail).setPassword(password).clickLoginButton();
    }

    public String getValidationSummaryErrorMessage() {
        return ui.waitUntilVisible(VALIDATION_SUMMARY_ERRORS_LOCATORS).getText();
    }

    public String getLoginValidationErrorMessage() {
        return ui.waitUntilVisible(LOGIN_VALIDATION_ERROR_LOCATOR).getText();
    }

    public String getEmailValidationError() {
        return ui.waitUntilVisible(EMAIL_FIELD_VALIDATION_ERROR).getText();
    }

}