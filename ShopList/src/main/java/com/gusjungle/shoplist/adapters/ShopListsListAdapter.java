package com.gusjungle.shoplist.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.gusjungle.shoplist.R;
import com.gusjungle.shoplist.data.ShopList;

import java.util.List;

/**
 * Created by Gustavo on 5/19/13.
 */
public class ShopListsListAdapter extends ArrayAdapter<ShopList> {

    private Context context;
    private int layoutResourceId;
    private List<ShopList> data = null;

    public ShopListsListAdapter(Context context, int textViewResourceId, List<ShopList> objects) {
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
            holder.shopListName = (TextView) row.findViewById(R.id.shopList_name_label);
            holder.shopListBudget = (TextView) row.findViewById(R.id.shopList_budget_label);

            row.setTag(holder);
        }
        else
        {
            holder = (ShopListHolder)row.getTag();
        }

        ShopList shopList = data.get(position);

        holder.shopListName.setText(shopList.getName());
        holder.shopListBudget.setText("" + shopList.getBudget());

        return row;
    }

    private static class ShopListHolder
    {
        TextView shopListName;
        TextView shopListBudget;
    }
}
