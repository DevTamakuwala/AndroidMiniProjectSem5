package com.example.miniproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView

class ActivityAttenderHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attender_home)
        val sharedPreferences = applicationContext.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        DatabaseHelper(this)
        val dbHelper = DatabaseHelper(this)
        val gridView = findViewById<GridView>(R.id.gridview)
        val res = dbHelper.showMenu()
        val menuadapter = attenderMenuAdapter(this, res)
        gridView.adapter = menuadapter
        val btnLogout = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("uname")
            editor.remove("pass")
            editor.apply()
            intent = Intent(this,ActivityAttender::class.java)
            startActivity(intent)
            finish()
        }
    }
}