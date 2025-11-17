package com.example.demowebshop.factories;

import com.example.demowebshop.config.ProductConfig;
import com.example.demowebshop.model.Product;

public class ProductFactory {

    private ProductConfig productConfig;

    public ProductFactory(ProductConfig productConfig) {
        this.productConfig = productConfig;
    }

    public Product getDefaultBookProduct() {
        Product book = new Product();
        book.setTitle(productConfig.getBookTitle());
        book.setPriceText(productConfig.getBookPrice());
        book.setFullDescription(productConfig.getBookFullDescription());
        book.setShortDescription(productConfig.getBookShortDescription());

        return book;
    }
}
