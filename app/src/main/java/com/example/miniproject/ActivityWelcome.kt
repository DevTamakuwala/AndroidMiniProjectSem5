package com.example.miniproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityWelcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val btnAttender = findViewById<Button>(R.id.btnAttender)
        val btnAdmin = findViewById<Button>(R.id.btnAdmin)
        btnAttender.setOnClickListener {
            intent = Intent(this, ActivityAttender::class.java)
            startActivity(intent)
        }
        btnAdmin.setOnClickListener {
            intent = Intent(this, ActivityAdmin::class.java)
            startActivity(intent)
        }
    }
}