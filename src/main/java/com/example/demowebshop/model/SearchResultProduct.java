package com.example.demowebshop.model;

import java.math.BigDecimal;

public class SearchResultProduct {

    private final String title;
    private final String priceText;
    private final BigDecimal price;
    private final String productUrl;

    public SearchResultProduct(String title,
                               String priceText,
                               BigDecimal price,
                               String productUrl) {
        this.title = title;
        this.priceText = priceText;
        this.price = price;
        this.productUrl = productUrl;
    }

    public String getTitle() {
        return title;
    }


    public String getPriceText() {
        return priceText;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getProductUrl() {
        return productUrl;
    }

    @Override
    public String toString() {
        return "SearchResultProduct{" +
                "title='" + title + '\'' +
                ", priceText='" + priceText + '\'' +
                ", price=" + price +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}
