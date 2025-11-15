package com.example.demowebshop.enums;

public enum SortBy {

    POSITION("Position"),
    NAME_A_TO_Z("Name: A to Z"),
    NAME_Z_TO_A("Name: Z to A"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    CREATED_ON("Created on");

    String visibleText;

    SortBy(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return this.visibleText;
    }
}
