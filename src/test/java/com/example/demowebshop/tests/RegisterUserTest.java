package com.example.demowebshop.tests;

import com.example.demowebshop.factories.UserFactory;
import com.example.demowebshop.model.User;
import com.example.demowebshop.pages.header.RegisterPage;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class RegisterUserTest extends BaseTest {

    private User userToRegister;
    private User existingUser;
    private RegisterPage registerPage;
    private UserFactory userFactory;


    @BeforeClass
    public void initialize() {
        userFactory = new UserFactory(config.auth());
        userToRegister = userFactory.createRandomUser();
        existingUser = userFactory.getDefaultUser();
    }

    @Test(priority = 1)
    public void checkErrorMessagesOnIncorrectRegistration() {
        registerPage = pages
                .home()
                .open()
                .goToRegister().clickRegister();//simply click register button without inserting anything

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(registerPage.getFirstNameValidationError(), "First name is required.");
        softAssert.assertEquals(registerPage.getLastNameValidationError(), "Last name is required.");
        softAssert.assertEquals(registerPage.getEmailValidationError(), "Email is required.");
        softAssert.assertEquals(registerPage.getPasswordValidationError(), "Password is required.");
        softAssert.assertEquals(registerPage.getConfirmPasswordValidationError(), "Password is required.");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void registerWithExistingEmail() {
        registerPage = pages
                .home()
                .open()
                .goToRegister()
                .register(existingUser);
        assertEquals(registerPage.getValidationSummary(), "The specified email already exists");
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
    public void sendWrongEmail(String wrongEmail) {
        registerPage = pages
                .home()
                .open()
                .goToRegister()
                .setFirstName(userToRegister.getFirstName())
                .setLastName(userToRegister.getLastName())
                .setEmail(wrongEmail)//set the wrong email
                .setPassword(userToRegister.getPassword());//set the next element for the validation to kick-in
        assertEquals(registerPage.getEmailValidationError(), "Wrong email");
    }

    @Test(priority = 4)
    public void registerUserHappyPath() {
        boolean isRegistrationSuccessful = pages
                .home()
                .open()
                .goToRegister()
                .register(userToRegister)
                .isRegistrationSuccessful();
        assertTrue(isRegistrationSuccessful);
    }

    @Test(dependsOnMethods = "registerUserHappyPath")
    public void logoutRegisteredUser() {
        pages.home().header().logout();
    }
}
