package com.example.miniproject

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminCategoryActivity : AppCompatActivity() {

    private lateinit var coursesGV: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_category)

        val btnAddMenu = findViewById<FloatingActionButton>(R.id.btnAdd)
        val dbHelper = DatabaseHelper(this)

        coursesGV = findViewById(R.id.categoryList)
        val res = dbHelper.showCategory()

        val list = arrayListOf<String>()
        val list1 = arrayListOf<String>()
        while (res!!.moveToNext()) {
            val row1 = res.getString(0)
            list1.add(row1)
            val row = res.getString(1)
            list.add(row)
        }
        val adapter = categoryAdapter(list1, list, this)
        coursesGV.adapter = adapter

        btnAddMenu.setOnClickListener {
            intent = Intent(this, AdminAddCategory::class.java)
            startActivity(intent)
            finish()
        }
    }
}