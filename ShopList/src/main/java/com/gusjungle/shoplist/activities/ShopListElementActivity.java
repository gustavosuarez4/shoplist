package com.gusjungle.shoplist.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.ShopListApplication;
import com.gusjungle.shoplist.data.ShopList;
import com.gusjungle.shoplist.data.ShopListApplicationData;
import com.gusjungle.shoplist.data.ShopListElement;
import com.gusjungle.shoplist.utils.NavigationUtils;

/**
 * Created by Gustavo on 5/18/13.
 */
public class ShopListElementActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_element);

        ShopListApplicationData shopListApplicationData = ShopListApplication.getShopListApplicationData();

        ShopList shopList = shopListApplicationData.getShopLists().get(NavigationUtils.getCurrentShopListIndex());
        ShopListElement shopListElement = shopList.getElements().get(NavigationUtils.getCurrentShopListElementIndex());

        String shopListElementName = shopListElement.getName();
        setTitle(shopListElementName.toUpperCase().charAt(0) + shopListElementName.substring(1));

        EditText shopListElementPriceEditText = (EditText) findViewById(R.id.shop_list_element_price);
        shopListElementPriceEditText.setText("" + shopListElement.getPrice());

        EditText shopListElementQuantityEditText = (EditText) findViewById(R.id.shop_list_element_quantity);
        shopListElementQuantityEditText.setText("" + shopListElement.getQuantity());

        double taxAmount = shopListElement.isTaxable() ? shopListElement.getPrice() * shopList.getTaxRate() : 0;

        EditText shopListElementTaxEditText = (EditText) findViewById(R.id.shop_list_element_tax_amount);
        shopListElementTaxEditText.setText("" + taxAmount);
        shopListElementTaxEditText.setEnabled(false);

        EditText shopListElementTotalAmountEditText = (EditText) findViewById(R.id.shop_list_element_total_amount);
        shopListElementTotalAmountEditText.setText("" + (shopListElement.getPrice() + taxAmount));
        shopListElementTotalAmountEditText.setEnabled(false);

        CheckBox taxableCheckBox = (CheckBox) findViewById(R.id.shop_list_element_taxable);
        taxableCheckBox.setChecked(shopListElement.isTaxable());

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavigationUtils.goBack(this, ShopListActivity.class);
                break;
        }
        return true;
    }

}
