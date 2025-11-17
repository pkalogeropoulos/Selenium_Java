package com.example.demowebshop.config;

import java.math.BigDecimal;
import java.util.Properties;

public class ProductConfig {

    // ==========================
    // PROPERTY KEYS
    // ==========================

    private static final String BOOK_TITLE_KEY            = "product.book.title";
    private static final String BOOK_SHORT_DESC_KEY       = "product.book.description.short";
    private static final String BOOK_FULL_DESC_KEY        = "product.book.description.full";
    private static final String BOOK_PRICE_KEY            = "product.book.price";
    private static final String BOOK_AVAILABILITY_KEY     = "product.book.availability";

    // ==========================
    // FIELDS
    // ==========================

    private final String bookTitle;
    private final String bookShortDescription;
    private final String bookFullDescription;
    private final String bookPrice;
    private final String bookAvailability;

    // ==========================
    // CONSTRUCTOR
    // ==========================

    /**
     * Constructs ProductConfig by reading all product-related fields
     * from the provided Properties instance.
     */
    public ProductConfig(Properties props) {
        this.bookTitle            = props.getProperty(BOOK_TITLE_KEY);
        this.bookShortDescription = props.getProperty(BOOK_SHORT_DESC_KEY);
        this.bookFullDescription  = props.getProperty(BOOK_FULL_DESC_KEY);

        // Parse number safely to BigDecimal
        String priceStr = props.getProperty(BOOK_PRICE_KEY);
        this.bookPrice = priceStr;

        this.bookAvailability = props.getProperty(BOOK_AVAILABILITY_KEY);
    }

    // ==========================
    // GETTERS
    // ==========================

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookShortDescription() {
        return bookShortDescription;
    }

    public String getBookFullDescription() {
        return bookFullDescription;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookAvailability() {
        return bookAvailability;
    }
}
