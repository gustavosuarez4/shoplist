package com.gusjungle.shoplist.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopList implements Serializable {

    private int shopListId;
    private String name;
    private double budget;
    private List<ShopListElement> elements = new ArrayList<ShopListElement>();

    public int getShopListId() {
        return shopListId;
    }

    public void setShopListId(int shopListId) {
        this.shopListId = shopListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<ShopListElement> getElements() {
        return elements;
    }

    public void setElements(List<ShopListElement> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return getName();
    }
}
