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
import android.widget.Toast;

import java.util.ArrayList;

public class attenderAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> listNumber = new ArrayList<String>();
    private final Context context;


    public attenderAdapter(ArrayList<String> listNumber, ArrayList<String> list, Context context) {
        this.list = list;
        this.listNumber = listNumber;
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

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.attender_list, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        TextView listItemTextNumber = (TextView) view.findViewById(R.id.list_item_string_number);
        listItemText.setText(list.get(position));
        listItemTextNumber.setText(listNumber.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("my_pref",Context.MODE_PRIVATE);
                //do something
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.deleteData(listNumber.get(position));
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
