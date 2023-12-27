package com.example.miniproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class AdminOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_orders)

        val dbHelper = DatabaseHelper(this)

        val res = dbHelper.seeOrder

        //generate list
        val list = arrayListOf<String>()
        val list1 = arrayListOf<String>()
        val list2 = arrayListOf<String>()
        val list3 = arrayListOf<String>()
        val list4 = arrayListOf<String>()
        while (res.moveToNext()) {
            val row1 = res.getString(0)
            list1.add(row1)
            val row = res.getString(1)
            list.add(row)
            val row2 = res.getString(2)
            list2.add(row2)
            val row3 = res.getString(3)
            list3.add(row3)
            val row4 = res.getString(4)
            list4.add(row4)
        }

        //instantiate custom adapter
        val adapter = orderAdapter2(list1, list,list2,list3,list4,this)

        //handle listview and assign adapter
        val lView = findViewById<View>(R.id.attenderList) as ListView
        lView.adapter = adapter

    }
}