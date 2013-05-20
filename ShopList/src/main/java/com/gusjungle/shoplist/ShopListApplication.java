package com.gusjungle.shoplist;

import android.app.Application;
import com.gusjungle.shoplist.data.ShopListApplicationData;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListApplication extends Application {

    private static ShopListApplicationData shopListApplicationData = new ShopListApplicationData();

    public static ShopListApplicationData getShopListApplicationData() {
        return shopListApplicationData;
    }

    public static void setShopListApplicationData(ShopListApplicationData shopListApplicationData) {
        ShopListApplication.shopListApplicationData = shopListApplicationData;
    }
}
