package com.gusjungle.shoplist.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.utils.NavigationUtils;

/**
 * Created by Gustavo on 5/18/13.
 */
public class ShopListElementDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_element_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavigationUtils.goHome(this, MainActivity.class);
                break;
        }
        return true;
    }

}
