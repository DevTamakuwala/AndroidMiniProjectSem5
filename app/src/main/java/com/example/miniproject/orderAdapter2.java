package com.example.miniproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class orderAdapter2 extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> listNumber = new ArrayList<String>();
    private ArrayList<String> listQty = new ArrayList<String>();
    private ArrayList<String> listNum = new ArrayList<String>();
    private ArrayList<String> listPtice = new ArrayList<String>();
    private final Context context;


    public orderAdapter2(ArrayList<String> listNumber, ArrayList<String> list, ArrayList<String> listQty, ArrayList<String> listNum, ArrayList<String> listPtice, Context context) {
        this.list = list;
        this.listNumber = listNumber;
        this.listQty = listQty;
        this.listNum = listNum;
        this.listPtice = listPtice;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_layout2, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        TextView listItemTextNumber = (TextView) view.findViewById(R.id.list_item_string_number);
        TextView list_item_string_tblNo = (TextView) view.findViewById(R.id.list_item_string_tblNo);
        listItemText.setText(list.get(position));
        listItemTextNumber.setText(listNumber.get(position));
        list_item_string_tblNo.setText("Table Number "+listNumber.get(position));

        return view;
    }
}