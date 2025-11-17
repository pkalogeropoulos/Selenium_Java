package com.example.demowebshop.pages.customerInfo;

import com.example.demowebshop.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdersPage extends BasePage<OrdersPage> {

    private final By ORDER_LIST = By.cssSelector("div.order-list div.section.order-item");
    private final By ORDER_TITLE = By.cssSelector("div.title");
    private final By ORDER_STATUS = By.cssSelector("ul.info li:nth-of-type(1)");
    private final By ORDER_DATE = By.cssSelector("ul.info li:nth-of-type(2)");
    private final By ORDER_TOTAL_AMOUNT = By.cssSelector("ul.info li:nth-of-type(3)");

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getAllVisibleOrderElements() {
        return ui.waitUntilVisibleAll(ORDER_LIST);
    }


    public String getStatusForOrder(String orderNumber) {
        List<WebElement> allOrders = this.getAllVisibleOrderElements();
        for (WebElement currentOrder : allOrders) {
            String title = currentOrder.findElement(ORDER_TITLE).getText();
            if (extractOrderNumberFromTitle(title).equals(orderNumber)) {
                return extractStatusFromOrder(currentOrder.findElement(ORDER_STATUS).getText());
            }
        }
        return null;
    }

    public String getDateForOrder(String orderNumber) {
        List<WebElement> allOrders = this.getAllVisibleOrderElements();
        for (WebElement currentOrder : allOrders) {
            String title = currentOrder.findElement(ORDER_TITLE).getText();
            if (extractOrderNumberFromTitle(title).equals(orderNumber)) {
                return extractDateFromOrder(currentOrder.findElement(ORDER_DATE).getText());
            }
        }
        return null;
    }

    public String getTotalAmountForOrder(String orderNumber) {
        List<WebElement> allOrders = this.getAllVisibleOrderElements();
        for (WebElement currentOrder : allOrders) {
            String title = currentOrder.findElement(ORDER_TITLE).getText();
            if (extractOrderNumberFromTitle(title).equals(orderNumber)) {
                return extractTotalAmountFromOrder(currentOrder.findElement(ORDER_TOTAL_AMOUNT).getText());
            }
        }
        return null;
    }

    private String extractOrderNumberFromTitle(String title) {
        Pattern pattern = Pattern.compile("Order Number:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(title);

        if (matcher.find()) {
            return matcher.group(1);  // The digits after "Order Number:"
        }
        return null;
    }

    private String extractStatusFromOrder(String orderStatusText) {
        Pattern pattern = Pattern.compile("Order status:\\s*(.+)");
        Matcher matcher = pattern.matcher(orderStatusText);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    private String extractDateFromOrder(String dateText) {
        Pattern pattern = Pattern.compile("Order Date:\\s*(.+)");
        Matcher matcher = pattern.matcher(dateText);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    private String extractTotalAmountFromOrder(String amountText) {
        Pattern pattern = Pattern.compile("Order Total:\\s*([0-9]+(?:\\.[0-9]{1,2})?)");
        Matcher matcher = pattern.matcher(amountText);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
