package com.example.demowebshop.tests.search;

import com.example.demowebshop.pages.ProductDetailsPage;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BookProductTest extends BaseTest {

    @Test(description = "Open Fiction Ex Product page, expect all Book properties to appear correctly")
    public void openFictionBookProductPage() {
        ProductDetailsPage fictionExDetailsPage = pages
                .home()
                .open()
                .searchFor("Fiction")
                .openProductByTitle("Fiction EX");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(fictionExDetailsPage.getProductTitle(), config.product().getBookTitle());
        softAssert.assertEquals(fictionExDetailsPage.getShortDescription(), config.product().getBookShortDescription());
        softAssert.assertEquals(fictionExDetailsPage.getFullDescription(), config.product().getBookFullDescription());
        softAssert.assertEquals(fictionExDetailsPage.getPrice(), config.product().getBookPrice());
        softAssert.assertAll();
    }
}
