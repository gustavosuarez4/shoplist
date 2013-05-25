package com.gusjungle.shoplist;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import com.gusjungle.shoplist.data.ShopList;
import com.gusjungle.shoplist.data.ShopListApplicationData;
import com.gusjungle.shoplist.data.ShopListElement;

import java.util.List;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListApplication extends Application {

    private static ShopListApplicationData shopListApplicationData = new ShopListApplicationData();

    @Override
    public void onCreate() {
        super.onCreate();

        if( 0 != ( super.getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE ) ) {

            //init of shopListApplicationData
            String[] shopListNames = new String[]{"Groceries","Parts","Office","Gifts"};
            boolean[] shopListNamesFlags = new boolean[shopListNames.length];
            String[] shopListElementNames = new String[]{"Teeth brush","Staple","Mouse","Keyboard","Deodorant","Galaxy","Android","Watch","Belt","Bread","Milk","Eggs"};

            int shopLists = (int)((shopListNames.length - 1) * Math.random()) + 1;

            for(int i = 0; i < shopLists; i++) {
                ShopList shopList = new ShopList();

                String name;
                while(true) {
                    int shopListIndex = (int)(shopListNames.length * Math.random());
                    if(!shopListNamesFlags[shopListIndex]) {
                        name = shopListNames[shopListIndex];
                        shopListNamesFlags[shopListIndex] = true;
                        break;
                    }
                }

                shopList.setBudget(Math.round(50000 * Math.random())/100);
                shopList.setShopListId(i);
                shopList.setName(name);

                List<ShopListElement> shopListElementList = shopList.getElements();
                int shopListElements = (int)(shopListElementNames.length * Math.random());
                for (int j = 0; j < shopListElements; j++) {
                    ShopListElement shopListElement = new ShopListElement();

                    shopListElement.setPrice(Math.round((shopList.getBudget() * 100 / 5 ) * Math.random())/100);
                    shopListElement.setTaxable(Math.random() < 0.05);
                    shopListElement.setQuantity((int)(Math.random() > 0.15 ? 1 : 5 * Math.random()));
                    shopListElement.setName(shopListElementNames[(int)(Math.random() * shopListElementNames.length)]);

                    shopListElementList.add(shopListElement);
                }
                shopListApplicationData.getShopLists().add(shopList);
            }
        }
    }

    public static ShopListApplicationData getShopListApplicationData() {
        return shopListApplicationData;
    }

    public static void setShopListApplicationData(ShopListApplicationData shopListApplicationData) {
        ShopListApplication.shopListApplicationData = shopListApplicationData;
    }
}
