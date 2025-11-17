package com.example.demowebshop.model;

import io.opentelemetry.api.internal.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String title;
    private String priceText;
    private String productUrl;
    private String fullDescription;
    private String shortDescription;

    public BigDecimal getPriceBigDecimal() {
        return StringUtils.isNullOrEmpty(priceText)? null : new BigDecimal((priceText));
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", priceText='" + priceText + '\'' +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}
