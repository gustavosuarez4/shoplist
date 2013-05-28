package com.gusjungle.shoplist.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.ShopListApplication;
import com.gusjungle.shoplist.data.ShopList;
import com.gusjungle.shoplist.data.ShopListApplicationData;
import com.gusjungle.shoplist.data.ShopListElement;
import com.gusjungle.shoplist.utils.NavigationUtils;

import java.text.NumberFormat;

/**
 * Created by Gustavo on 5/18/13.
 */
public class ShopListElementActivity extends Activity {

    private ShopList shopList;
    private ShopListElement shopListElement;

    private CheckBox taxableCheckBox;
    private EditText shopListElementPriceEditText;
    private EditText shopListElementQuantityEditText;

    private EditText shopListElementTaxEditText;
    private EditText shopListElementTotalAmountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        NumberFormat currencyFormatter = ShopListApplication.getCurrencyFormatter();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_element);

        ShopListApplicationData shopListApplicationData = ShopListApplication.getShopListApplicationData();

        shopList = shopListApplicationData.getShopLists().get(NavigationUtils.getCurrentShopListIndex());
        shopListElement = shopList.getElements().get(NavigationUtils.getCurrentShopListElementIndex());

        String shopListElementName = shopListElement.getName();
        setTitle(shopListElementName.toUpperCase().charAt(0) + shopListElementName.substring(1));

        shopListElementPriceEditText = (EditText) findViewById(R.id.shop_list_element_price);
        shopListElementPriceEditText.setText(currencyFormatter.format(shopListElement.getPrice()));

        shopListElementQuantityEditText = (EditText) findViewById(R.id.shop_list_element_quantity);
        shopListElementQuantityEditText.setText("" + shopListElement.getQuantity());

        shopListElementTaxEditText = (EditText) findViewById(R.id.shop_list_element_tax_amount);
        shopListElementTaxEditText.setText(currencyFormatter.format(getTaxAmount()));
        shopListElementTaxEditText.setEnabled(false);

        shopListElementTotalAmountEditText = (EditText) findViewById(R.id.shop_list_element_total_amount);
        shopListElementTotalAmountEditText.setText(currencyFormatter.format(getTotalAmount()));
        shopListElementTotalAmountEditText.setEnabled(false);

        taxableCheckBox = (CheckBox) findViewById(R.id.shop_list_element_taxable);
        taxableCheckBox.setChecked(shopListElement.isTaxable());

        Button saveButton = (Button) findViewById(R.id.shop_list_element_save_button);
        saveButton.setOnClickListener(saveShopListElementButtonOnClickListener);

        Button cancelButton = (Button) findViewById(R.id.shop_list_element_cancel_button);
        cancelButton.setOnClickListener(cancelShopListElementButtonOnClickListener);

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

    private View.OnClickListener saveShopListElementButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            try {
                Number elementPrice = ShopListApplication.getCurrencyFormatter().parse(shopListElementPriceEditText.getText().toString());
                shopListElement.setPrice(elementPrice.doubleValue());
            } catch (Exception e) {}
            try {
                shopListElement.setQuantity(Integer.parseInt(shopListElementQuantityEditText.getText().toString()));
            } catch (Exception e) {}
            try {
                shopListElement.setTaxable(taxableCheckBox.isChecked());
            } catch (Exception e) {}

            //shopListElementTaxEditText.setText("" + getTaxAmount());
            //shopListElementTotalAmountEditText.setText("" + getTotalAmount());

            NavigationUtils.goBack(ShopListElementActivity.this, ShopListActivity.class);
        }
    };

    private View.OnClickListener cancelShopListElementButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NavigationUtils.goBack(ShopListElementActivity.this, ShopListActivity.class);
        }
    };

    // TODO: include custom tax rate (By Element)
    private double getTaxAmount() {
        return shopListElement.isTaxable() ? shopListElement.getPrice() * shopList.getTaxRate() : 0;
    }

    // TODO: improve efficiency
    private double getTotalAmount() {
        return shopListElement.getPrice() + getTaxAmount();
    }
}
