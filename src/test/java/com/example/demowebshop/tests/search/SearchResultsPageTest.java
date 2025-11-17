package com.example.demowebshop.tests.search;

import com.example.demowebshop.enums.SortBy;
import com.example.demowebshop.model.Product;
import com.example.demowebshop.pages.SearchResultsPage;
import com.example.demowebshop.tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class SearchResultsPageTest extends BaseTest {

    private SearchResultsPage searchResultsPage;
    private final String COMPUTER_SEARCH_TERM = "Computer";

    @BeforeClass
    public void initialize() {
        searchResultsPage = pages.home().open().searchFor(COMPUTER_SEARCH_TERM);
    }

    @Test
    public void sortByPriceAscending() {
        List<Product> computersSortedByPrice = searchResultsPage.sortBy(SortBy.PRICE_LOW_TO_HIGH).getVisibleProducts();

        // Extract only prices
        List<BigDecimal> actualPrices = computersSortedByPrice.stream().map(Product::getPriceBigDecimal).collect(Collectors.toList());

// Create a sorted copy
        List<BigDecimal> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Comparator.naturalOrder());

// Assert equality
        assertEquals(sortedPrices, actualPrices, "Products are NOT sorted by price ascending");
    }

    @Test
    public void sortByPriceDescending() {
        List<Product> computersSortedByPrice = searchResultsPage.sortBy(SortBy.PRICE_HIGH_TO_LOW).getVisibleProducts();

        // Extract only prices
        List<BigDecimal> actualPrices = computersSortedByPrice.stream().map(Product::getPriceBigDecimal).collect(Collectors.toList());

        // Create a sorted copy
        List<BigDecimal> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Comparator.reverseOrder());

        // Assert equality
        assertEquals(sortedPrices, actualPrices, "Products are NOT sorted by price descending");
    }

    @Test
    public void sortByNameAscending() {
        List<Product> computersSortedByName = searchResultsPage.sortBy(SortBy.NAME_A_TO_Z).getVisibleProducts();

        List<String> actualProductNames = computersSortedByName.stream().map(Product::getTitle).collect(Collectors.toList());

        // Create a sorted copy
        List<String> sortedNames = new ArrayList<>(actualProductNames);
        sortedNames.sort(Comparator.naturalOrder());

        // Assert equality
        assertEquals(sortedNames, actualProductNames, "Products are NOT sorted by product name ascending");
    }

    @Test
    public void sortByNameDescending() {
        List<Product> computersSortedByName = searchResultsPage.sortBy(SortBy.NAME_Z_TO_A).getVisibleProducts();

        List<String> actualProductNames = computersSortedByName.stream().map(Product::getTitle).collect(Collectors.toList());

        // Create a sorted copy
        List<String> sortedNames = new ArrayList<>(actualProductNames);
        sortedNames.sort(Comparator.reverseOrder());

        // Assert equality
        assertEquals(sortedNames, actualProductNames, "Products are NOT sorted by product name descending");
    }
}