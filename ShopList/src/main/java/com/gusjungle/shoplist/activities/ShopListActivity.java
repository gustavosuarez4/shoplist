package com.gusjungle.shoplist.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.ShopListApplication;
import com.gusjungle.shoplist.adapters.ShopListAdapter;
import com.gusjungle.shoplist.data.ShopList;
import com.gusjungle.shoplist.data.ShopListApplicationData;
import com.gusjungle.shoplist.data.ShopListElement;
import com.gusjungle.shoplist.utils.NavigationUtils;

/**
 * @author Gustavo on 5/19/13 9:12 PM
 */
public class ShopListActivity extends Activity {

    private ShopList shopList;
    private View promptView;
    private TextView availableBudgetTextView;

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

        //sets budget on small header text
        TextView availableBudgetSmallTextView = (TextView) findViewById(R.id.shop_list_available_budget_small);
        availableBudgetSmallTextView.setText(availableBudgetSmallTextView.getText().toString() + (shopList != null ? shopList.getBudget() : 0));

        availableBudgetTextView = (TextView) findViewById(R.id.shop_list_available_budget_value);
        setAvailableBudget();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_list, menu);
        return true;
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

    private void setAvailableBudget() {
        if(shopList == null) {
            availableBudgetTextView.setText("0");
            return;
        }

        if(shopList.getElements() == null || shopList.getElements().size() == 0) {
            availableBudgetTextView.setText("" + shopList.getBudget());
            return;
        }

        double availableBudget = shopList.getBudget();
        for(ShopListElement elem : shopList.getElements()) {
            availableBudget -= elem.getPrice();
        }

        availableBudgetTextView.setText("" + availableBudget);

        if(availableBudget < 0) {
            availableBudgetTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void addNewShopListElement() {

        promptView = getLayoutInflater().inflate(R.layout.prompt_add_new_shop_list_element, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton(getString(R.string.add_new_shopList_element), AddNewShopListElementListener)
                .setNegativeButton(getString(R.string.cancel), NavigationUtils.DialogCancelActionListener)
                // TODO: add support for detailed addition using .setNeutralButton()
                .create();

        alertDialog.show();
    }

    private DialogInterface.OnClickListener AddNewShopListElementListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            EditText newShopListElementNameTextView = (EditText) promptView.findViewById(R.id.new_shop_list_element_name);
            EditText newShopListElementPriceTextView = (EditText) promptView.findViewById(R.id.new_shop_list_element_price);

            String newShopListElementName = newShopListElementNameTextView.getText().toString();
            Double newShopListElementPrice = null;

            try {
                newShopListElementPrice = Double.parseDouble(newShopListElementPriceTextView.getText().toString());
            } catch (NumberFormatException e) { }

            // TODO: validate shopListElementName
            // TODO: validate shopListElementPrice

            ShopListElement shopListElement = new ShopListElement();
            shopListElement.setName(newShopListElementName);
            shopListElement.setPrice(newShopListElementPrice);

            shopList.getElements().add(shopListElement);
            setAvailableBudget();
        }
    };
}
