package com.example.demowebshop.pages;

public enum SearchCategory {
    ALL("All Departments"),
    BOOKS("Books"),
    ELECTRONICS("Electronics"),
    CLOTHING_SHOES_JEWELRY("Clothing, Shoes & Jewelry"),
    HOME_KITCHEN("Home & Kitchen"),
    BEAUTY_PERSONAL_CARE("Beauty & Personal Care"),
    SPORTS_OUTDOORS("Sports & Outdoors"),
    TOYS_GAMES("Toys & Games"),
    VIDEO_GAMES("Video Games"),
    HEALTH_PERSONAL_CARE("Health & Personal Care"),
    PET_SUPPLIES("Pet Supplies");

    private final String visibleText;

    SearchCategory(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return visibleText;
    }
}
