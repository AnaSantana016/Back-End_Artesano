package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Product;

import java.time.Instant;

public class HistoryProduct {

    private Product product;
    private Instant purchaseDate;
    private int amount;

    public HistoryProduct(Product product, Instant purchaseDate, int amount) {
        this.product = product;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
