package com.example.demowebshop.enums;

public enum PageSize {

    FOUR("4"),
    EIGHT("8"),
    TWELVE("12");

    String visibleText;

    PageSize(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return this.visibleText;
    }
}
