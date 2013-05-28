package com.gusjungle.shoplist.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.ShopListApplication;
import com.gusjungle.shoplist.adapters.ShopListsListAdapter;
import com.gusjungle.shoplist.utils.NavigationUtils;
import com.gusjungle.shoplist.data.ShopList;
import com.gusjungle.shoplist.data.ShopListApplicationData;

public class MainActivity extends Activity {

    private View promptView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShopListApplicationData shopListApplicationData = ShopListApplication.getShopListApplicationData();

        ListView shopListsListView = (ListView) findViewById(R.id.shopLists_list_view);
        //shopListsListView.setOnItemLongClickListener(shopListsListViewOnItemLongClickListener);
        shopListsListView.setOnItemClickListener(shopListsListViewOnItemClickListener);

        ShopListsListAdapter shopListsListAdapter = (ShopListsListAdapter) shopListsListView.getAdapter();
        if(shopListsListAdapter == null) {
            shopListsListAdapter = new ShopListsListAdapter(this, R.layout.view_shop_list_list, shopListApplicationData.getShopLists());
            shopListsListView.setAdapter(shopListsListAdapter);
        }

        //Button addElementButton = (Button) findViewById(R.id.addElementButton);
        //addElementButton.setOnClickListener(addElementButtonOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //getContentResolver();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_shopList:
                addNewShopList();
                break;
            case R.id.action_settings:
                //TODO: settings
                break;
            case R.id.action_help:
                //TODO: help
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewShopList() {

        promptView = getLayoutInflater().inflate(R.layout.prompt_add_new_shop_list, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton(getString(R.string.add_new_shopList), AddNewShopListListener)
                .setNegativeButton(getString(R.string.cancel), NavigationUtils.DialogCancelActionListener)
                // TODO: add support for detailed addition using .setNeutralButton()
                .create();

        alertDialog.show();
    }

    private DialogInterface.OnClickListener AddNewShopListListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            ShopListApplicationData shopListApplicationData = ShopListApplication.getShopListApplicationData();

            EditText newShopListNameTextView = (EditText) promptView.findViewById(R.id.new_shopList_name_textView);
            EditText newShopListBudgetTextView = (EditText) promptView.findViewById(R.id.new_shopList_budget_textView);

            String newShopListName = newShopListNameTextView.getText().toString();
            Double newShopListBudget = null;
            try {
                newShopListBudget = Double.parseDouble(newShopListBudgetTextView.getText().toString());
            } catch (NumberFormatException e) { }

            // TODO: validate shopListName
            // TODO: validate shopListBudget

            ShopList shopList = new ShopList();
            shopList.setName(newShopListName);
            shopList.setBudget(newShopListBudget);
            shopList.setShopListId(shopListApplicationData.getShopLists().size());

            shopListApplicationData.getShopLists().add(shopList);
        }
    };

//    private AdapterView.OnItemLongClickListener shopListsListViewOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
//        @Override
//        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//            Intent intent = new Intent(MainActivity.this, ShopListElementActivity.class);
//            startActivity(intent);
//            return false;
//        }
//    };

    private AdapterView.OnItemClickListener shopListsListViewOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, ShopListActivity.class);

            NavigationUtils.setCurrentShopListIndex(i);

            startActivity(intent);
        }
    };

}
