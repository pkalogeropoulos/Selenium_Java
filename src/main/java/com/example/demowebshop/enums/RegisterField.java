package com.example.demowebshop.enums;

public enum RegisterField {
    FIRST_NAME("FirstName"),
    LAST_NAME("LastName"),
    EMAIL("Email"),
    PASSWORD("Password"),
    CONFIRM_PASSWORD("ConfirmPassword");

    private final String fieldName;

    RegisterField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
