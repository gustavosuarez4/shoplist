package com.gusjungle.shoplist.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListApplicationData {

    private List<ShopList> shopLists = new ArrayList<ShopList>();
    private Double taxPercent;

    public List<ShopList> getShopLists() {
        return shopLists;
    }

    public void setShopLists(List<ShopList> shopLists) {
        this.shopLists = shopLists;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }
}
