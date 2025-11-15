package com.example.demowebshop.tests;

import com.example.demowebshop.factories.UserFactory;
import com.example.demowebshop.model.User;
import com.example.demowebshop.pages.LoginPage;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginUserTest extends BaseTest {

    private User existingUser;
    private LoginPage loginPage;
    private SoftAssert softAssert;

    @BeforeClass
    public void initialize() {
        existingUser = UserFactory.getDefaultUser();
        softAssert = new SoftAssert();
    }

    @Test
    public void loginWithNonExistingUser() {
        String nonExistingEmail = UserFactory.getRandomEmail();
        loginPage = pages.home().open().header().goToLogin().setEmail(nonExistingEmail).setPassword(existingUser.getPassword());
        loginPage.clickLoginButton();
        softAssert.assertEquals(loginPage.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.");
        softAssert.assertEquals(loginPage.getLoginValidationErrorMessage(), "No customer account found");
    }

}
