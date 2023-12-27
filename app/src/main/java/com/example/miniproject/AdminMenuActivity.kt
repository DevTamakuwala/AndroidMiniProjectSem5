package com.example.miniproject

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminMenuActivity : AppCompatActivity() {
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        val btnAddMenu = findViewById<FloatingActionButton>(R.id.btnAdd)
        val btnCategory = findViewById<Button>(R.id.btnCategory)
        val dbHelper = DatabaseHelper(this)
        val gridView = findViewById<GridView>(R.id.gridview)

        val res = dbHelper.showMenu()
        val menuadapter = menuAdaapter(this, res)
        gridView.adapter = menuadapter


        btnAddMenu.setOnClickListener {
            intent = Intent(this, AdminAddMenu::class.java)
            startActivity(intent)
            finish()
        }

        btnCategory.setOnClickListener {
            intent = Intent(this, AdminCategoryActivity::class.java)
            startActivity(intent)
        }
    }
}