package com.gusjungle.shoplist.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by Gustavo on 5/19/13.
 */
public class NavigationUtils {

    public static void goHome(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    public static DialogInterface.OnClickListener DialogCancelActionListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog,int id) {
            dialog.cancel();
        }
    };

}
