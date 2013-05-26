package com.gusjungle.shoplist.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Gustavo on 5/19/13.
 */
public class NavigationUtils {

    public static int currentShopListIndex;
    public static int currentShopListElementIndex;

    public static void goBack(Activity activity, Class clazz) {
        goBack(activity, clazz, null);
    }

    public static void goBack(Activity activity, Class clazz, Bundle extras) {
        Intent intent = new Intent(activity, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(extras != null)
            intent.putExtras(extras);
        activity.startActivity(intent);
    }

    public static DialogInterface.OnClickListener DialogCancelActionListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog,int id) {
            dialog.cancel();
        }
    };

    public static int getCurrentShopListIndex() {
        return currentShopListIndex;
    }

    public static void setCurrentShopListIndex(int currentShopListIndex) {
        NavigationUtils.currentShopListIndex = currentShopListIndex;
    }

    public static int getCurrentShopListElementIndex() {
        return currentShopListElementIndex;
    }

    public static void setCurrentShopListElementIndex(int currentShopListElementIndex) {
        NavigationUtils.currentShopListElementIndex = currentShopListElementIndex;
    }
}
