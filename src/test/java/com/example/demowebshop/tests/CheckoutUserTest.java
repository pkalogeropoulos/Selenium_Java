package com.example.demowebshop.tests;

import com.example.demowebshop.factories.UserFactory;
import com.example.demowebshop.model.User;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutUserTest extends BaseTest {

    private User registeredUser;

    @BeforeClass
    public void initialize() {
        registeredUser = UserFactory.getDefaultUser();
        //login with our default/registered user
        pages.home().open().goToLogin().performLogin(registeredUser.getEmail(), registeredUser.getPassword());
    }

    @Test
    public void addProductToCart() {
        pages.home().open().menu().goToComputers().goToDesktops();
        System.out.println("here");
    }


}
