package com.example.demowebshop.pages.menu;

import com.example.demowebshop.pages.BasePage;
import com.example.demowebshop.pages.ProductDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BooksPage extends BasePage<BooksPage> {

    private static final By BOOK_PRODUCT_ITEM = By.cssSelector("div.item-box");
    private static final By BOOK_TITLE = By.cssSelector("h2.product-title");

    public BooksPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage clickBookWithTitle(String title) {
        ui.waitUntilClickable(By.linkText(title)).click();
        return new ProductDetailsPage((driver));
    }
}
