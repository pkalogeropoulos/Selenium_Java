package com.example.demowebshop.pages.header;

import com.example.demowebshop.pages.BasePage;
import com.example.demowebshop.pages.customerInfo.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CustomerInfoPage extends BasePage<CustomerInfoPage> {

    // ===== LOCATORS =====

    // Navigation: Orders
    private final By ORDERS_LINK = By.linkText("Orders");

    // Title: "My account - Customer info"
    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1");

    // Gender radios
    private static final By GENDER_MALE_RADIO =
            By.id("gender-male");
    private static final By GENDER_FEMALE_RADIO =
            By.id("gender-female");

    // Core fields
    private static final By FIRST_NAME_INPUT =
            By.id("FirstName");
    private static final By LAST_NAME_INPUT =
            By.id("LastName");
    private static final By EMAIL_INPUT =
            By.id("Email");
    private static final By COMPANY_INPUT =
            By.id("Company");

    // Date of birth dropdowns
    private static final By DOB_DAY_SELECT =
            By.name("DateOfBirthDay");
    private static final By DOB_MONTH_SELECT =
            By.name("DateOfBirthMonth");
    private static final By DOB_YEAR_SELECT =
            By.name("DateOfBirthYear");

    // Newsletter (checkbox)
    private static final By NEWSLETTER_CHECKBOX =
            By.id("Newsletter");

    // Save button
    private static final By SAVE_BUTTON =
            By.name("save-info-button"); // sometimes "save-info-button" as id/name

    // Success / result message
    private static final By SUCCESS_MESSAGE =
            By.cssSelector("div.result");

    // Validation errors (field-level)
    private static final By FIRST_NAME_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='FirstName']");
    private static final By LAST_NAME_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='LastName']");
    private static final By EMAIL_VALIDATION_ERROR =
            By.cssSelector("span.field-validation-error[data-valmsg-for='Email']");

    public CustomerInfoPage(WebDriver driver) {
        super(driver);
    }

    //  Navigation
    public OrdersPage goToOrders() {
        ui.waitUntilClickable(ORDERS_LINK).click();
        return new OrdersPage(driver);
    }

    // ===== GENDER =====

    public CustomerInfoPage selectMaleGender() {
        ui.waitUntilClickable(GENDER_MALE_RADIO).click();
        return self();
    }

    public CustomerInfoPage selectFemaleGender() {
        ui.waitUntilClickable(GENDER_FEMALE_RADIO).click();
        return self();
    }

    // ===== TEXT FIELDS =====

    public CustomerInfoPage enterFirstName(String firstName) {
        WebElement el = ui.waitUntilVisible(FIRST_NAME_INPUT);
        el.clear();
        el.sendKeys(firstName);
        return self();
    }

    public CustomerInfoPage enterLastName(String lastName) {
        WebElement el = ui.waitUntilVisible(LAST_NAME_INPUT);
        el.clear();
        el.sendKeys(lastName);
        return self();
    }

    public CustomerInfoPage enterEmail(String email) {
        WebElement el = ui.waitUntilVisible(EMAIL_INPUT);
        el.clear();
        el.sendKeys(email);
        return self();
    }

    public CustomerInfoPage enterCompany(String company) {
        WebElement el = ui.waitUntilVisible(COMPANY_INPUT);
        el.clear();
        el.sendKeys(company);
        return self();
    }

    // ===== DATE OF BIRTH =====

    public CustomerInfoPage selectBirthDay(String day) {
        WebElement el = ui.waitUntilVisible(DOB_DAY_SELECT);
        new Select(el).selectByVisibleText(day);
        return self();
    }

    public CustomerInfoPage selectBirthMonth(String month) {
        WebElement el = ui.waitUntilVisible(DOB_MONTH_SELECT);
        new Select(el).selectByVisibleText(month);
        return self();
    }

    public CustomerInfoPage selectBirthYear(String year) {
        WebElement el = ui.waitUntilVisible(DOB_YEAR_SELECT);
        new Select(el).selectByVisibleText(year);
        return self();
    }

    /**
     * Convenience method: set full date of birth.
     * Expects exact visible texts as they appear in the dropdowns.
     */
    public CustomerInfoPage setDateOfBirth(String day, String month, String year) {
        return selectBirthDay(day)
                .selectBirthMonth(month)
                .selectBirthYear(year);
    }

    // ===== NEWSLETTER =====

    public CustomerInfoPage setNewsletterSubscription(boolean subscribe) {
        WebElement checkbox = ui.waitUntilClickable(NEWSLETTER_CHECKBOX);
        boolean isChecked = checkbox.isSelected();
        if (isChecked != subscribe) {
            checkbox.click();
        }
        return self();
    }

    // ===== SAVE ACTIONS =====

    /**
     * Clicks Save and returns this page, expecting it to remain on the same page.
     * Typically used when you expect success and want to read the result message.
     */
    public CustomerInfoPage clickSave() {
        ui.waitUntilClickable(SAVE_BUTTON).click();
        return self();
    }

    /**
     * Clicks Save and waits for the success message to appear.
     */
    public CustomerInfoPage saveExpectingSuccess() {
        clickSave();
        ui.waitUntilVisible(SUCCESS_MESSAGE);
        return self();
    }

    // ===== GETTERS / ASSERTION HELPERS =====

    public String getSuccessMessage() {
        return ui.waitUntilVisible(SUCCESS_MESSAGE)
                .getText()
                .trim();
    }

    public String getFirstNameValidationError() {
        return ui.waitUntilVisible(FIRST_NAME_VALIDATION_ERROR)
                .getText()
                .trim();
    }

    public String getLastNameValidationError() {
        return ui.waitUntilVisible(LAST_NAME_VALIDATION_ERROR)
                .getText()
                .trim();
    }

    public String getEmailValidationError() {
        return ui.waitUntilVisible(EMAIL_VALIDATION_ERROR)
                .getText()
                .trim();
    }

    public boolean isSuccessMessageDisplayed() {
        return !driver.findElements(SUCCESS_MESSAGE).isEmpty();
    }

    public boolean isFirstNameValidationErrorDisplayed() {
        return !driver.findElements(FIRST_NAME_VALIDATION_ERROR).isEmpty();
    }

    public boolean isLastNameValidationErrorDisplayed() {
        return !driver.findElements(LAST_NAME_VALIDATION_ERROR).isEmpty();
    }

    public boolean isEmailValidationErrorDisplayed() {
        return !driver.findElements(EMAIL_VALIDATION_ERROR).isEmpty();
    }

    // ===== CONVENIENCE: FULL UPDATE FLOW =====

    public CustomerInfoPage updateCustomerInfo(String gender, String firstName, String lastName,
                                               String email, String company,
                                               String day, String month, String year,
                                               boolean subscribeToNewsletter) {
        if ("male".equalsIgnoreCase(gender)) {
            selectMaleGender();
        } else if ("female".equalsIgnoreCase(gender)) {
            selectFemaleGender();
        }

        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterCompany(company)
                .setDateOfBirth(day, month, year)
                .setNewsletterSubscription(subscribeToNewsletter)
                .saveExpectingSuccess();
    }
}
