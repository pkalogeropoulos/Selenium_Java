package com.example.demowebshop.tests;

import com.example.demowebshop.factories.UserFactory;
import com.example.demowebshop.model.User;
import com.example.demowebshop.pages.header.LoginPage;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginUserTest extends BaseTest {

    private User existingUser;
    private LoginPage loginPage;
    private SoftAssert softAssert;
    private UserFactory userFactory;

    @BeforeClass
    public void initialize() {
        userFactory = new UserFactory(config.auth());
        existingUser = userFactory.getDefaultUser();
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void loginWithNonExistingUser() {
        String nonExistingEmail = userFactory.getRandomEmail();
        loginPage = pages.home().open().header().goToLogin().setEmail(nonExistingEmail).setPassword(existingUser.getPassword());
        loginPage.clickLoginButton();
        softAssert.assertEquals(loginPage.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.");
        softAssert.assertEquals(loginPage.getLoginValidationErrorMessage(), "No customer account found");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void loginWithWrongPassword() {
        loginPage = pages.home().open().header().goToLogin().setEmail(existingUser.getEmail()).setPassword("wrongPassword");
        loginPage.clickLoginButton();
        softAssert.assertEquals(loginPage.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.");
        softAssert.assertEquals(loginPage.getLoginValidationErrorMessage(), "The credentials provided are incorrect");
        softAssert.assertAll();
    }

    @DataProvider(name = "wrongEmails")
    public String[] getWrongEmails() {
        return new String[]{
                "test",
                "test@test@com",
                "test@.test.com"
        };
    }

    @Test(dataProvider = "wrongEmails", priority = 3)
    public void setWrongEmail(String wrongEmail) {
        loginPage = pages.home().open().header().goToLogin().setEmail(wrongEmail).setPassword("wrongPassword");
        assertEquals(loginPage.getEmailValidationError(), "Please enter a valid email address.");
    }

    @Test(priority = 4)
    public void performLoginHappyPath() {
        boolean isUserLoggedIn = pages
                .home()
                .open()
                .header()
                .goToLogin()
                .performLogin(existingUser.getEmail(), existingUser.getPassword())
                .header()
                .isLoggedIn();

        softAssert.assertTrue(isUserLoggedIn);
        softAssert.assertEquals(pages.home().header().getCustomerInfoAccountText(), existingUser.getEmail());//check that the username/email appears correctly
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "performLoginHappyPath")
    public void logout() {
        pages.home().header().logout();
    }
}
