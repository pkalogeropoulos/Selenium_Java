package com.example.demowebshop.pages.orders.enums;

import org.openqa.selenium.By;

public enum PaymentMethod {

    CASH_ON_DELIVERY(By.id("paymentmethod_0")),
    CHECK_MONEY_ORDER(By.id("paymentmethod_1")),
    CREDIT_CARD(By.id("paymentmethod_2")),
    PURCHASE_ORDER(By.id("paymentmethod_3"));

    private By selector;

    PaymentMethod(By selector) {
        this.selector = selector;
    }

    public By selector() {
        return this.selector;
    }
}
