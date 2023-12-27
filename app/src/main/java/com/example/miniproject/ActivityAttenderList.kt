package com.example.miniproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityAttenderList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attender_list)

        val dbHelper = DatabaseHelper(this)

        val btnAddAttender = findViewById<FloatingActionButton>(R.id.btnAdd)
        val res = dbHelper.allData

        //generate list
        val list = arrayListOf<String>()
        val list1 = arrayListOf<String>()
        while (res.moveToNext()) {
            val row1 = res.getString(0)
            list1.add(row1)
            val row = res.getString(1)
            list.add(row)
        }

        //instantiate custom adapter
        val adapter = attenderAdapter(list1, list, this)

        //handle listview and assign adapter
        val lView = findViewById<View>(R.id.attenderList) as ListView
        lView.adapter = adapter

        btnAddAttender.setOnClickListener {
            intent = Intent(this, ActivityAddAttender::class.java)
            startActivity(intent)
            finish()
        }
    }
}