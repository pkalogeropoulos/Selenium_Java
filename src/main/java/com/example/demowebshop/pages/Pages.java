package com.example.demowebshop.pages;

import org.openqa.selenium.WebDriver;

public class Pages {

    private final WebDriver driver;

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private LoginPage loginPage;

    public Pages(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage home() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public SearchResultsPage searchResults() {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage(driver);
        }
        return searchResultsPage;
    }

    public LoginPage login() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
}
