package com.gusjungle.shoplist.data;

import java.io.Serializable;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListElement implements Serializable {

    private String name;
    private double price;
    private int quantity = 1;
    private boolean taxable = true;
    private double elementTaxRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public double getElementTaxRate() {
        return elementTaxRate;
    }

    public void setElementTaxRate(double elementTaxRate) {
        this.elementTaxRate = elementTaxRate;
    }
}
