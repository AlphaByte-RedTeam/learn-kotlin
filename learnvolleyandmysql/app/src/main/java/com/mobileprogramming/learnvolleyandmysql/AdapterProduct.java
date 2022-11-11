package com.mobileprogramming.learnvolleyandmysql;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProduct extends BaseAdapter {

    private Activity activity;
    private ArrayList<Product> arrayListData = new ArrayList<Product>();
    private static LayoutInflater inflater = null;

    public AdapterProduct(Activity a, ArrayList<Product> d) {
        activity = a;
        arrayListData = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayListData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (view == null)
            v = inflater.inflate(R.layout.list_product, null);
        TextView textViewNama = v.findViewById(R.id.textViewNama);
        Product product = arrayListData.get(position);
        textViewNama.setText(product.getNama());
        return v;
    }
}
