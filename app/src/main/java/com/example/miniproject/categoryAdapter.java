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

public class categoryAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> listNumber = new ArrayList<String>();
    private final Context context;


    public categoryAdapter(ArrayList<String> listNumber, ArrayList<String> list, Context context) {
        this.list = list;
        this.listNumber = listNumber;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listNumber.size();
    }

    @Override
    public Object getItem(int pos) {
        return listNumber.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.category_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        TextView listItemTextNumber = (TextView) view.findViewById(R.id.list_item_string_number);
        listItemText.setText(list.get(position).toString());
        listItemTextNumber.setText(listNumber.get(position).toString());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("my_pref",Context.MODE_PRIVATE);
                //do something
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.deleteCat(String.valueOf(listNumber.get(position)));
                listNumber.remove(listNumber.get(position)); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
