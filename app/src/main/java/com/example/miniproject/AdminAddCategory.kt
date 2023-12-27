package com.example.miniproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AdminAddCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_category)

        val edCategory = findViewById<EditText>(R.id.edCategory)
        val btnAdItem = findViewById<Button>(R.id.btnAdItem)
        val dbHelper = DatabaseHelper(this)
        btnAdItem.setOnClickListener {
            dbHelper.addCategory(edCategory.text.toString())
            intent = Intent(this, AdminCategoryActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}