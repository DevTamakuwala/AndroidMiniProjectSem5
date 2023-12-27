package com.example.miniproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityAdminHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        val sharedPreferences = applicationContext.getSharedPreferences("my_pref", Context.MODE_PRIVATE)


        val dbHelper = DatabaseHelper(this)
        val btnAdd = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnAdd)
        val btnLogout = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnLogout)

        btnAdd.setOnClickListener {
            intent = Intent(this,ActivityAddAttender::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("aname")
            editor.remove("pass")
            val success = editor.commit()
            intent = Intent(this,ActivityAdmin::class.java)
            startActivity(intent)
            finish()
        }
    }
}