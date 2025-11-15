package com.example.demowebshop.pages;

import com.example.demowebshop.enums.PageSize;
import com.example.demowebshop.enums.SortBy;
import com.example.demowebshop.model.SearchResultProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage<SearchResultsPage> {

    // ==========================
    // LOCATORS
    // ==========================

    // Whole search results area
    private final By SEARCH_RESULTS_CONTAINER_LOCATOR =
            By.cssSelector("div.search-results");

    // Individual product tiles (each result)
    private final By PRODUCT_ITEM_LOCATOR =
            By.cssSelector("div.search-results div.item-box");

    private final By PRODUCT_SHORT_DESCRIPTION_LOCATOR =
            By.cssSelector("div.product-details div.description");

    // Inside each product item
    private final By PRODUCT_TITLE_LINK_LOCATOR =
            By.cssSelector("h2.product-title a");

    private final By PRODUCT_PRICE_LOCATOR =
            By.cssSelector("div.prices span.price");

    private final By PRODUCT_OLD_PRICE_LOCATOR =
            By.cssSelector("div.prices span.old-price");

    private final By PRODUCT_ADD_TO_CART_BUTTON_LOCATOR =
            By.cssSelector("input.button-2.product-box-add-to-cart-button");

    private final By PRODUCT_ADD_TO_WISHLIST_BUTTON_LOCATOR =
            By.cssSelector("input.button-2.add-to-wishlist-button");

    private final By PRODUCT_ADD_TO_COMPARE_BUTTON_LOCATOR =
            By.cssSelector("input.button-2.add-to-compare-list-button");

    // When there are no results
    private final By NO_RESULTS_MESSAGE_LOCATOR =
            By.cssSelector("div.search-results div.no-result");


    // View mode / sorting / paging controls
    private final By VIEW_AS_GRID_LINK_LOCATOR =
            By.cssSelector("a.viewmode-icon.grid");
    private final By VIEW_AS_LIST_LINK_LOCATOR =
            By.cssSelector("a.viewmode-icon.list");

    private final By SORT_BY_DROPDOWN_LOCATOR =
            By.id("products-orderby");
    private final By PAGE_SIZE_DROPDOWN_LOCATOR =
            By.id("products-pagesize");

    // ==========================
    // CONSTRUCTOR
    // ==========================

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    // ==========================
    // BASIC STATE HELPERS
    // ==========================

    /**
     * Waits until the search results container is visible.
     */
    public SearchResultsPage waitForResults() {
        ui.waitUntilVisible(SEARCH_RESULTS_CONTAINER_LOCATOR);
        return self();
    }

    /**
     * Returns true if a "no results" message is shown.
     */
    public boolean isNoResultsMessageDisplayed() {
        return !driver.findElements(NO_RESULTS_MESSAGE_LOCATOR).isEmpty();
    }

    /**
     * Returns the number of product tiles shown in the results.
     */
    public int getResultsCount() {
        List<WebElement> items = driver.findElements(PRODUCT_ITEM_LOCATOR);
        return items.size();
    }

    // ==========================
    // PRODUCT DATA ACCESS
    // ==========================

    /**
     * Returns all currently visible products on the search results page
     * as strongly-typed model objects, so tests never touch WebElements.
     */
    public List<SearchResultProduct> getVisibleProducts() {
        // Wait until at least one result is visible
        List<WebElement> items = ui.waitUntilVisibleAll(PRODUCT_ITEM_LOCATOR);

        return items.stream()
                .map(this::mapToSearchResultProduct)
                .collect(Collectors.toList());
    }

    // Internal mapper from WebElement -> SearchResultProduct
    private SearchResultProduct mapToSearchResultProduct(WebElement item) {
        // Title and URL
        WebElement titleLink = item.findElement(PRODUCT_TITLE_LINK_LOCATOR);
        String title = titleLink.getText();
        String url = titleLink.getAttribute("href");

        // Short description (optional, so be defensive)
        String shortDescription = "";
        List<WebElement> descriptions = item.findElements(PRODUCT_SHORT_DESCRIPTION_LOCATOR);
        if (!descriptions.isEmpty()) {
            shortDescription = descriptions.get(0).getText();
        }

        // Price text & parsed price
        String priceText = "";
        BigDecimal price = null;
        List<WebElement> prices = item.findElements(PRODUCT_PRICE_LOCATOR);
        if (!prices.isEmpty()) {
            priceText = prices.get(0).getText();        // e.g. "$1,500.00"
            String digits = priceText.replaceAll("[^0-9.,]", ""); // "1500.00"
            if (!digits.isBlank()) {
                price = new BigDecimal(digits.replace(",", ""));
            }
        }

        return new SearchResultProduct(
                title,
                priceText,
                price,
                url
        );
    }
    /**
     * Returns all result titles as a list of strings.
     */
    public List<String> getAllResultTitles() {
        List<WebElement> items = driver.findElements(PRODUCT_ITEM_LOCATOR);
        return items.stream()
                .map(item -> item.findElement(PRODUCT_TITLE_LINK_LOCATOR).getText())
                .collect(Collectors.toList());
    }

    /**
     * Returns the title of a product by index (0-based).
     */
    public String getResultTitleByIndex(int index) {
        WebElement item = getProductItemByIndex(index);
        return item.findElement(PRODUCT_TITLE_LINK_LOCATOR).getText();
    }

    /**
     * Returns the current price of a product by index (as string).
     * (You can parse to BigDecimal in your test or add another helper.)
     */
    public String getResultPriceByIndex(int index) {
        WebElement item = getProductItemByIndex(index);
        return item.findElement(PRODUCT_PRICE_LOCATOR).getText();
    }

    /**
     * Returns true if any of the result titles contains the given text (case-insensitive).
     */
    public boolean anyResultTitleContains(String text) {
        String lower = text.toLowerCase();
        return getAllResultTitles().stream()
                .anyMatch(t -> t.toLowerCase().contains(lower));
    }

    // ==========================
    // PRODUCT ACTIONS
    // ==========================

    /**
     * Clicks on the product title at a given index to open its details page.
     */
    public ProductDetailsPage openProductDetailsByIndex(int index) {
        WebElement item = getProductItemByIndex(index);
        item.findElement(PRODUCT_TITLE_LINK_LOCATOR).click();
        return new ProductDetailsPage(driver);
    }

    /**
     * Opens a specific product based on its title text.
     * This is a case-insensitive contains() match.
     *
     * @param title The text to look for in the product title.
     * @return ProductDetailsPage representing the opened product page.
     */
    public ProductDetailsPage openProductByTitle(String title) {
        List<WebElement> items = driver.findElements(PRODUCT_ITEM_LOCATOR);

        String lowerTitle = title.toLowerCase();

        for (WebElement item : items) {
            String itemTitle = item.findElement(PRODUCT_TITLE_LINK_LOCATOR).getText();
            if (itemTitle.toLowerCase().contains(lowerTitle)) {
                item.findElement(PRODUCT_TITLE_LINK_LOCATOR).click();
                return new ProductDetailsPage(driver);
            }
        }

        throw new IllegalArgumentException(
                "No product found with title containing: \"" + title + "\". " +
                        "Available titles: " + items.stream()
                        .map(i -> i.findElement(PRODUCT_TITLE_LINK_LOCATOR).getText())
                        .toList()
        );
    }

    /**
     * Clicks the "Add to cart" button for the product at the given index.
     * Returns this page for fluent chaining.
     */
    public SearchResultsPage addProductToCartByIndex(int index) {
        WebElement item = getProductItemByIndex(index);
        item.findElement(PRODUCT_ADD_TO_CART_BUTTON_LOCATOR).click();
        return self();
    }

    public SearchResultsPage addProductToWishlistByIndex(int index) {
        WebElement item = getProductItemByIndex(index);
        item.findElement(PRODUCT_ADD_TO_WISHLIST_BUTTON_LOCATOR).click();
        return self();
    }

    public SearchResultsPage addProductToCompareByIndex(int index) {
        WebElement item = getProductItemByIndex(index);
        item.findElement(PRODUCT_ADD_TO_COMPARE_BUTTON_LOCATOR).click();
        return self();
    }

    // ==========================
    // VIEW / SORT / PAGE SIZE
    // ==========================

    public SearchResultsPage viewAsGrid() {
        ui.waitUntilClickable(VIEW_AS_GRID_LINK_LOCATOR).click();
        return self();
    }

    public SearchResultsPage viewAsList() {
        ui.waitUntilClickable(VIEW_AS_LIST_LINK_LOCATOR).click();
        return self();
    }

    /**
     * Select sort option by visible text, e.g. "Name: A to Z".
     */
    public SearchResultsPage sortBy(SortBy sortBy) {
        WebElement dropdown = ui.waitUntilVisible(SORT_BY_DROPDOWN_LOCATOR);
        new Select(dropdown).selectByVisibleText(sortBy.getVisibleText());
        return self();
    }

    /**
     * Select how many items to display per page, e.g. "4", "8", "12".
     */
    public SearchResultsPage setPageSize(PageSize pageSize) {
        WebElement dropdown = ui.waitUntilVisible(PAGE_SIZE_DROPDOWN_LOCATOR);
        new Select(dropdown).selectByVisibleText(pageSize.getVisibleText());
        return self();
    }

    // ==========================
    // INTERNAL HELPERS
    // ==========================

    private WebElement getProductItemByIndex(int index) {
        List<WebElement> items = driver.findElements(PRODUCT_ITEM_LOCATOR);
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException(
                    "No product at index " + index + ". Total results: " + items.size()
            );
        }
        return items.get(index);
    }
}
