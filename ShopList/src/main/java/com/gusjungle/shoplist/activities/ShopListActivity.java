package com.gusjungle.shoplist.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.ShopListApplication;
import com.gusjungle.shoplist.adapters.ShopListAdapter;
import com.gusjungle.shoplist.data.ShopList;
import com.gusjungle.shoplist.data.ShopListApplicationData;
import com.gusjungle.shoplist.utils.NavigationUtils;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListActivity extends Activity {

    private ShopList shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShopListApplicationData shopListApplicationData = ShopListApplication.getShopListApplicationData();

        Bundle extras = getIntent().getExtras();
        shopList = shopListApplicationData.getShopLists().get(extras.getInt("ShopListIndex"));

        //sets the layout
        setContentView(R.layout.activity_shop_list);

        //adds back button on activity icon
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //changes the activity title to the name of the list
        String shopListName = this.shopList.getName();
        setTitle(shopListName.toUpperCase().charAt(0) + shopListName.substring(1));

        ListView shopListsListView = (ListView) findViewById(R.id.shopList_list_view);
        //shopListsListView.setOnItemClickListener(shopListsListViewOnItemClickListener);

        ShopListAdapter shopListAdapter = (ShopListAdapter) shopListsListView.getAdapter();
        if(shopListAdapter == null) {
            shopListAdapter = new ShopListAdapter(this, R.layout.view_shop_list_element_list, shopList.getElements());
            shopListsListView.setAdapter(shopListAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavigationUtils.goHome(this, MainActivity.class);
                break;
            case R.id.action_new_shopListElement:
                addNewShopListElement();
                break;
        }
        return true;
    }

    private void addNewShopListElement() {
//        promptsView = getLayoutInflater().inflate(R.layout.prompt_add_new_shop_list, null);
//
//        AlertDialog alertDialog = new AlertDialog.Builder(this)
//                .setView(promptsView)
//                .setPositiveButton(getString(R.string.add_new_shopList), AddNewShopListListener)
//                .setNegativeButton(getString(R.string.cancel), NavigationUtils.DialogCancelActionListener)
//                        // TODO: add support for detailed addition using .setNeutralButton()
//                .create();
//
//        alertDialog.show();
    }

}
