package com.example.demowebshop.tests;

import com.example.demowebshop.factories.CardFactory;
import com.example.demowebshop.factories.ProductFactory;
import com.example.demowebshop.factories.UserFactory;
import com.example.demowebshop.model.Product;
import com.example.demowebshop.model.User;
import com.example.demowebshop.pages.ProductDetailsPage;
import com.example.demowebshop.pages.customerInfo.OrdersPage;
import com.example.demowebshop.pages.orders.OrderCompletedPage;
import com.example.demowebshop.pages.orders.enums.PaymentMethod;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutUserTest extends BaseTest {

    private User registeredUser;
    private UserFactory userFactory;
    private ProductFactory productFactory;
    private Product bookToPurchase;
    private String orderNumber;

    @BeforeClass
    public void initialize() {
        userFactory = new UserFactory((config.auth()));
        productFactory = new ProductFactory(config.product());
        registeredUser = userFactory.getDefaultUser();
        bookToPurchase = productFactory.getDefaultBookProduct();
        //login with our default/registered user
        pages.home().open().goToLogin().performLogin(registeredUser.getEmail(), registeredUser.getPassword());
    }

    @Test
    public void addProductToCart() {
        ProductDetailsPage productPage = pages.home().open().menu().goToBooks().clickBookWithTitle(bookToPurchase.getTitle()).addToCart();
        boolean notificationBarVisible = productPage.isNotificationBarVisible();
        assertTrue(notificationBarVisible);
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void checkoutFromCart() {
        //Go to cart page
        OrderCompletedPage orderCompletedPage = pages
                .home()
                .header()
                .goToShoppingCart()
                .clickTermsOfService()
                .proceedToCheckout()
                .continueFromBilling()
                .continueFromShippingAddress()
                .continueFromShippingMethod()
                .selectPaymentMethod(PaymentMethod.CREDIT_CARD)
                .continueFromPaymentMethod()
                .setCard(new CardFactory(config.card()).getDefaultCard())
                .continueFromPaymentInfo()
                .confirmOrder();

        assertEquals(orderCompletedPage.getOrderCompletedMessage(), "Your order has been successfully processed!");
        orderNumber = orderCompletedPage.getOrderNumber();
    }

    @Test(dependsOnMethods = "checkoutFromCart")
    public void checkOrdersPage() {
        OrdersPage ordersPage = pages
                .home()
                .open()
                .header()
                .goToCustomerInfoPage()
                .goToOrders();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(ordersPage.getStatusForOrder(orderNumber), "Pending");
        softAssert.assertEquals(ordersPage.getTotalAmountForOrder(orderNumber), bookToPurchase.getPriceText());
        softAssert.assertAll();
    }
}
