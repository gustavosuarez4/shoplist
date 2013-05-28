package com.gusjungle.shoplist.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.ShopListApplication;
import com.gusjungle.shoplist.data.ShopListElement;

import java.text.NumberFormat;
import java.util.List;

/**
 * @author Gustavo on 5/19/13 10:36 PM
 */
public class ShopListAdapter extends ArrayAdapter<ShopListElement> {

    private Context context;
    private int layoutResourceId;
    private List<ShopListElement> data = null;

    public ShopListAdapter(Context context, int textViewResourceId, List<ShopListElement> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.layoutResourceId = textViewResourceId;
        this.data = objects;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        ShopListHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ShopListHolder();
            holder.shopListElementName = (TextView) row.findViewById(R.id.shop_list_element_name_label);
            holder.shopListElementPrice = (TextView) row.findViewById(R.id.shop_list_element_price_label);

            row.setTag(holder);
        }
        else
        {
            holder = (ShopListHolder)row.getTag();
        }

        NumberFormat currencyFormatter = ShopListApplication.getCurrencyFormatter();

        ShopListElement shopListElement = data.get(position);

        holder.shopListElementName.setText(shopListElement.getName());
        holder.shopListElementPrice.setText(currencyFormatter.format(shopListElement.getPrice()));

        return row;
    }

    private static class ShopListHolder
    {
        TextView shopListElementName;
        TextView shopListElementPrice;
    }
}
