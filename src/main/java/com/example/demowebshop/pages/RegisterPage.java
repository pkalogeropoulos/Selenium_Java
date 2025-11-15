package com.example.demowebshop.pages;

import com.example.demowebshop.enums.Gender;
import com.example.demowebshop.enums.RegisterField;
import com.example.demowebshop.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage<RegisterPage> {

    // ==========================
    // LOCATORS
    // ==========================

    private final By GENDER_MALE_RADIO_LOCATOR = By.id("gender-male");
    private final By GENDER_FEMALE_RADIO_LOCATOR = By.id("gender-female");

    private final By FIRST_NAME_INPUT_LOCATOR = By.id("FirstName");
    private final By LAST_NAME_INPUT_LOCATOR = By.id("LastName");
    private final By EMAIL_INPUT_LOCATOR = By.id("Email");
    private final By PASSWORD_INPUT_LOCATOR = By.id("Password");
    private final By CONFIRM_PASSWORD_INPUT_LOCATOR = By.id("ConfirmPassword");

    private final By REGISTER_BUTTON_LOCATOR = By.id("register-button");
    private final By VALIDATION_SUMMARY_ERRORS = By.cssSelector("div.validation-summary-errors li");

    // --- Validation Error Locators ---
    private static final By FIRST_NAME_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='FirstName']");

    private static final By LAST_NAME_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='LastName']");

    private static final By EMAIL_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='Email']");

    private static final By PASSWORD_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='Password']");

    private static final By CONFIRM_PASSWORD_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='ConfirmPassword']");

    // Optional: result/validation
    private final By SUCCESS_MESSAGE_LOCATOR = By.cssSelector("div.result");
    private final By VALIDATION_SUMMARY_ERRORS_LOCATOR = By.cssSelector("div.validation-summary-errors");

    // ==========================
    // CONSTRUCTOR
    // ==========================

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // ==========================
    // NAVIGATION / PAGE SYNC
    // ==========================

    /**
     * Navigate directly to the register page.
     */
    public RegisterPage open() {
        driver.get(config.web().getBaseUrl() + "/register");
        return self();
    }

    /**
     * Wait until the First Name field is visible – page is ready.
     */
    public RegisterPage waitForPageToLoad() {
        ui.waitUntilVisible(FIRST_NAME_INPUT_LOCATOR);
        return self();
    }

    // ==========================
    // LOW-LEVEL FIELD SETTERS
    // ==========================

    public RegisterPage selectGender(Gender gender) {
        By locator = (gender == Gender.FEMALE)
                ? GENDER_FEMALE_RADIO_LOCATOR
                : GENDER_MALE_RADIO_LOCATOR;

        ui.waitUntilClickable(locator).click();
        return self();
    }

    public RegisterPage setFirstName(String firstName) {
        ui.type(FIRST_NAME_INPUT_LOCATOR, firstName);
        return self();
    }

    public RegisterPage setLastName(String lastName) {
        ui.type(LAST_NAME_INPUT_LOCATOR, lastName);
        return self();
    }

    public RegisterPage setEmail(String email) {
        ui.type(EMAIL_INPUT_LOCATOR, email);
        return self();
    }

    public RegisterPage setPassword(String password) {
        ui.type(PASSWORD_INPUT_LOCATOR, password);
        return self();
    }

    public RegisterPage setConfirmPassword(String password) {
        ui.type(CONFIRM_PASSWORD_INPUT_LOCATOR, password);
        return self();
    }


    public RegisterPage clickRegister() {
        ui.waitUntilClickable(REGISTER_BUTTON_LOCATOR).click();
        return self(); // after this, the page shows success or validation errors
    }

    // ==========================
    // HIGH-LEVEL ACTION: register(User)
    // ==========================

    /**
     * Fills the registration form using the given User model and submits it.
     *
     * @param user User object containing registration data.
     * @return this page (you can add a separate RegisterResultPage later if you want).
     */
    public RegisterPage register(User user) {
        waitForPageToLoad();

        selectGender(user.getGender());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setConfirmPassword(user.getPassword());   // assuming confirm == password

        clickRegister();
        return self();
    }

    // =========================
    // Get validation errors
    // =========================

    public String getValidationSummary() {
        return ui.waitUntilVisible(VALIDATION_SUMMARY_ERRORS_LOCATOR).getText();
    }
    public String getFirstNameValidationError() {
        return ui.waitUntilVisible(FIRST_NAME_VALIDATION_ERROR).getText();
    }

    public String getLastNameValidationError() {
        return ui.waitUntilVisible(LAST_NAME_VALIDATION_ERROR).getText();
    }

    public String getEmailValidationError() {
        return ui.waitUntilVisible(EMAIL_VALIDATION_ERROR).getText();
    }

    public String getPasswordValidationError() {
        return ui.waitUntilVisible(PASSWORD_VALIDATION_ERROR).getText();
    }

    public String getConfirmPasswordValidationError() {
        return ui.waitUntilVisible(CONFIRM_PASSWORD_VALIDATION_ERROR).getText();
    }

    // ==========================
    // READ / ASSERTION HELPERS
    // ==========================

    public String getSuccessMessage() {
        if (driver.findElements(SUCCESS_MESSAGE_LOCATOR).isEmpty()) {
            return null;
        }
        return driver.findElement(SUCCESS_MESSAGE_LOCATOR).getText();
    }

    public boolean isRegistrationSuccessful() {
        String msg = getSuccessMessage();
        return msg != null && msg.toLowerCase().contains("your registration completed");
    }
}
