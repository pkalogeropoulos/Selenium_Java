package com.example.demowebshop.pages.orders;

import com.example.demowebshop.model.Card;
import com.example.demowebshop.pages.BasePage;
import com.example.demowebshop.pages.orders.enums.PaymentMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckoutPage extends BasePage<CheckoutPage> {

    // ========= GENERAL =========
    private static final By PAGE_TITLE =
            By.cssSelector("div.page-title h1"); // "Checkout"

    // Progress steps (Cart, Address, Shipping, Payment, Confirm, Complete)
    private static final By CHECKOUT_STEPS =
            By.cssSelector("ol.checkout-progress li");

    // ========= BILLING ADDRESS SECTION =========
    // Existing address dropdown
    private static final By BILLING_ADDRESS_DROPDOWN =
            By.id("billing-address-select"); // standard nopCommerce id

    // "Ship to the same address" checkbox
    private static final By SHIP_TO_SAME_ADDRESS_CHECKBOX =
            By.id("ShipToSameAddress");

    // New billing address fields (standard Demo Web Shop/nopCommerce ids)
    private static final By BILLING_FIRST_NAME =
            By.id("BillingNewAddress_FirstName");
    private static final By BILLING_LAST_NAME =
            By.id("BillingNewAddress_LastName");
    private static final By BILLING_EMAIL =
            By.id("BillingNewAddress_Email");
    private static final By BILLING_COUNTRY =
            By.id("BillingNewAddress_CountryId");
    private static final By BILLING_CITY =
            By.id("BillingNewAddress_City");
    private static final By BILLING_ADDRESS1 =
            By.id("BillingNewAddress_Address1");
    private static final By BILLING_ZIP_POSTAL_CODE =
            By.id("BillingNewAddress_ZipPostalCode");
    private static final By BILLING_PHONE_NUMBER =
            By.id("BillingNewAddress_PhoneNumber");

    private static final By BILLING_CONTINUE_BUTTON =
            By.cssSelector("input.button-1.new-address-next-step-button, " +
                    "input.button-1.billing-address-next-step-button");

    // ========= SHIPPING ADDRESS SECTION =========
    private static final By SHIPPING_ADDRESS_DROPDOWN =
            By.id("shipping-address-select");
    private static final By SHIPPING_CONTINUE_BUTTON =
            By.cssSelector("#checkout-step-shipping input.button-1.new-address-next-step-button");

    // ========= SHIPPING METHOD SECTION =========
    // Shipping method radio list (name typically "shippingoption")
    private static final By SHIPPING_METHOD_RADIOS =
            By.cssSelector("input[name='shippingoption']");
    private static final By SHIPPING_METHOD_LABELS =
            By.cssSelector("#checkout-shipping-method-load .method-name label");

    private static final By SHIPPING_METHOD_CONTINUE_BUTTON =
            By.cssSelector("input.button-1.shipping-method-next-step-button");

    // ========= PAYMENT METHOD SECTION =========
    // Payment method radio list (name typically "paymentmethod")
    private static final By PAYMENT_METHOD_RADIOS =
            By.cssSelector("input[name='paymentmethod']");
    private static final By PAYMENT_METHOD_LABELS =
            By.cssSelector("#checkout-payment-method-load .method-name label");

    private static final By PAYMENT_METHOD_CONTINUE_BUTTON =
            By.cssSelector("input.button-1.payment-method-next-step-button");

    // ========= PAYMENT INFO SECTION =========
    private final By CREDIT_CARD_TYPE = By.id("CreditCardType");
    private final By CARDHOLDER_NAME = By.id("CardholderName");
    private final By CARD_NUMBER = By.id("CardNumber");
    private final By EXPIRATION_MONTH = By.id("ExpireMonth");
    private final By EXPIRATION_YEAR = By.id("ExpireYear");
    private final By CVV_CARD_CODE = By.id("CardCode");
    private static final By PAYMENT_INFO_CONTINUE_BUTTON =
            By.cssSelector("div#payment-info-buttons-container input.button-1.payment-info-next-step-button");

    // ========= CONFIRM ORDER SECTION =========
    private static final By CONFIRM_ORDER_BUTTON =
            By.cssSelector("input.button-1.confirm-order-next-step-button");

    // ========= ORDER COMPLETED SECTION =========
    private static final By ORDER_COMPLETED_TITLE =
            By.cssSelector("div.page-title h1"); // "Your order has been successfully processed!"
    private static final By ORDER_NUMBER =
            By.cssSelector("div.order-number strong"); // e.g. "Order #12345"

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ========= BILLING ADDRESS ACTIONS =========

    public CheckoutPage selectExistingBillingAddressByIndex(int index) {
        WebElement dropdown = ui.waitUntilVisible(BILLING_ADDRESS_DROPDOWN);
        var select = new Select(dropdown);
        select.selectByIndex(index);
        return self();
    }

    public CheckoutPage selectExistingBillingAddressByVisibleText(String text) {
        WebElement dropdown = ui.waitUntilVisible(BILLING_ADDRESS_DROPDOWN);
        var select = new Select(dropdown);
        select.selectByVisibleText(text);
        return self();
    }

    public CheckoutPage setShipToSameAddress(boolean value) {
        WebElement checkbox = ui.waitUntilClickable(SHIP_TO_SAME_ADDRESS_CHECKBOX);
        boolean isChecked = checkbox.isSelected();
        if (isChecked != value) {
            checkbox.click();
        }
        return self();
    }

    public CheckoutPage enterBillingFirstName(String firstName) {
        ui.waitUntilVisibleAll(BILLING_FIRST_NAME).clear();
        driver.findElement(BILLING_FIRST_NAME).sendKeys(firstName);
        return self();
    }

    public CheckoutPage enterBillingLastName(String lastName) {
        ui.waitUntilVisibleAll(BILLING_LAST_NAME).clear();
        driver.findElement(BILLING_LAST_NAME).sendKeys(lastName);
        return self();
    }

    public CheckoutPage enterBillingEmail(String email) {
        ui.waitUntilVisibleAll(BILLING_EMAIL).clear();
        driver.findElement(BILLING_EMAIL).sendKeys(email);
        return self();
    }

    public CheckoutPage selectBillingCountryByVisibleText(String country) {
        WebElement dropdown = ui.waitUntilVisible(BILLING_COUNTRY);
        var select = new Select(dropdown);
        select.selectByVisibleText(country);
        return self();
    }

    public CheckoutPage enterBillingCity(String city) {
        ui.waitUntilVisibleAll(BILLING_CITY).clear();
        driver.findElement(BILLING_CITY).sendKeys(city);
        return self();
    }

    public CheckoutPage enterBillingAddress1(String address1) {
        ui.waitUntilVisibleAll(BILLING_ADDRESS1).clear();
        driver.findElement(BILLING_ADDRESS1).sendKeys(address1);
        return self();
    }

    public CheckoutPage enterBillingZip(String zip) {
        ui.waitUntilVisibleAll(BILLING_ZIP_POSTAL_CODE).clear();
        driver.findElement(BILLING_ZIP_POSTAL_CODE).sendKeys(zip);
        return self();
    }

    public CheckoutPage enterBillingPhone(String phone) {
        ui.waitUntilVisibleAll(BILLING_PHONE_NUMBER).clear();
        driver.findElement(BILLING_PHONE_NUMBER).sendKeys(phone);
        return self();
    }

    /**
     * Convenience method to fill a new billing address in one go.
     */
    public CheckoutPage fillNewBillingAddress(String firstName,
                                              String lastName,
                                              String email,
                                              String country,
                                              String city,
                                              String address1,
                                              String zip,
                                              String phone) {
        return enterBillingFirstName(firstName)
                .enterBillingLastName(lastName)
                .enterBillingEmail(email)
                .selectBillingCountryByVisibleText(country)
                .enterBillingCity(city)
                .enterBillingAddress1(address1)
                .enterBillingZip(zip)
                .enterBillingPhone(phone);
    }

    public CheckoutPage continueFromBilling() {
        ui.waitUntilClickable(BILLING_CONTINUE_BUTTON).click();
        return self();
    }

    // ========= SHIPPING ADDRESS ACTIONS =========

    public CheckoutPage selectExistingShippingAddressByIndex(int index) {
        WebElement dropdown = ui.waitUntilVisible(SHIPPING_ADDRESS_DROPDOWN);
        var select = new Select(dropdown);
        select.selectByIndex(index);
        return self();
    }

    public CheckoutPage selectExistingShippingAddressByVisibleText(String text) {
        WebElement dropdown = ui.waitUntilVisible(SHIPPING_ADDRESS_DROPDOWN);
        var select = new Select(dropdown);
        select.selectByVisibleText(text);
        return self();
    }

    public CheckoutPage continueFromShippingAddress() {
        ui.waitUntilClickable(SHIPPING_CONTINUE_BUTTON).click();
        return self();
    }

    // ========= SHIPPING METHOD ACTIONS =========

    public CheckoutPage selectShippingMethodByName(String namePartial) {
        ui.waitUntilVisible(SHIPPING_METHOD_RADIOS);

        List<WebElement> radios = driver.findElements(SHIPPING_METHOD_RADIOS);
        List<WebElement> labels = driver.findElements(SHIPPING_METHOD_LABELS);

        for (int i = 0; i < radios.size() && i < labels.size(); i++) {
            String text = labels.get(i).getText().trim();
            if (text.toLowerCase().contains(namePartial.toLowerCase())) {
                radios.get(i).click();
                return self();
            }
        }

        throw new IllegalArgumentException("Shipping method containing '" + namePartial + "' not found");
    }

    public CheckoutPage continueFromShippingMethod() {
        ui.waitUntilClickable(SHIPPING_METHOD_CONTINUE_BUTTON).click();
        return self();
    }

    // ========= PAYMENT METHOD ACTIONS =========

    public CheckoutPage selectPaymentMethod(PaymentMethod paymentMethod) {
        ui.waitUntilClickable(paymentMethod.selector()).click();
        return self();
    }

    public CheckoutPage continueFromPaymentMethod() {
        ui.waitUntilClickable(PAYMENT_METHOD_CONTINUE_BUTTON).click();
        return self();
    }

    // ========= PAYMENT INFO ACTIONS =========

    public CheckoutPage selectCreditCardType(String type) {
        Select creditCardSelect = new Select(ui.waitUntilVisible(CREDIT_CARD_TYPE));
        creditCardSelect.selectByValue(type);
        return self();
    }

    public CheckoutPage setCardholderName(String name) {
        ui.type(CARDHOLDER_NAME, name);
        return self();
    }

    public CheckoutPage setCardNumber(String cardNumber) {
        ui.type(CARD_NUMBER, cardNumber);
        return self();
    }

    public CheckoutPage selectExpirationMonth(String month) {
        Select monthSelect = new Select(ui.waitUntilVisible(EXPIRATION_MONTH));
        monthSelect.selectByValue(month);
        return self();
    }

    public CheckoutPage selectExpirationYear(String year) {
        Select yearSelect = new Select(ui.waitUntilVisible(EXPIRATION_YEAR));
        yearSelect.selectByValue(year);
        return self();
    }

    public CheckoutPage setCardCode(String cvv) {
        ui.type(CVV_CARD_CODE, cvv);
        return self();
    }

    public CheckoutPage setCard(Card card) {
        this.selectCreditCardType(card.getCardType());
        this.setCardholderName(card.getCardholderName());
        this.setCardNumber(card.getCardNumber());
        this.selectExpirationMonth(card.getExpirationMonth());
        this.selectExpirationYear(card.getExpirationYear());
        this.setCardCode(card.getCvv());

        return self();
    }


    public CheckoutPage continueFromPaymentInfo() {
        ui.waitUntilClickable(PAYMENT_INFO_CONTINUE_BUTTON).click();
        return self();
    }

    // ========= CONFIRM ORDER / COMPLETION =========

    public OrderCompletedPage confirmOrder() {
        ui.waitUntilClickable(CONFIRM_ORDER_BUTTON).click();
        return new OrderCompletedPage((driver));
    }

    public boolean isOrderCompleted() {
        return !driver.findElements(ORDER_COMPLETED_TITLE).isEmpty()
                && driver.findElement(ORDER_COMPLETED_TITLE)
                .getText()
                .contains("successfully processed");
    }

    public String getOrderNumber() {
        return ui.waitUntilVisible(ORDER_NUMBER)
                .getText()
                .trim(); // e.g. "Order #12345"
    }
}
