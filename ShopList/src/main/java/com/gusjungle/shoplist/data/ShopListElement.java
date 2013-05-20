package com.gusjungle.shoplist.data;

import java.io.Serializable;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListElement implements Serializable {

    private String name;
    private int quantity;
    private double price;
    private boolean taxable = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }
}
