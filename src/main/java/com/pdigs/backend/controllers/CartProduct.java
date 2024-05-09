package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Product;

public class CartProduct {

    private Product product;
    private int amount;

    public CartProduct(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
