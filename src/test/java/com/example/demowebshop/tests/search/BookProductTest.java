package com.example.demowebshop.tests.search;

import com.example.demowebshop.factories.ProductFactory;
import com.example.demowebshop.model.Product;
import com.example.demowebshop.pages.ProductDetailsPage;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.print.Book;

public class BookProductTest extends BaseTest {

    private Product defaultBook;

    @BeforeClass
    public void initialize() {
        defaultBook = new ProductFactory(config.product()).getDefaultBookProduct();
    }

    @Test(description = "Open Fiction Ex Product page, expect all Book properties to appear correctly")
    public void openFictionBookProductPage() {
        ProductDetailsPage fictionExDetailsPage = pages
                .home()
                .open()
                .searchFor("Computing")
                .openProductByTitle(defaultBook.getTitle());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(fictionExDetailsPage.getProductTitle(), defaultBook.getTitle());
        softAssert.assertEquals(fictionExDetailsPage.getShortDescription(), defaultBook.getShortDescription());
        softAssert.assertEquals(fictionExDetailsPage.getFullDescription(), defaultBook.getFullDescription());
        softAssert.assertEquals(fictionExDetailsPage.getPriceText(), defaultBook.getPriceText());
        softAssert.assertAll();
    }
}
