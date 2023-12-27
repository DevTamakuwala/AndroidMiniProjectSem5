package com.example.miniproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_option_activity)

        val cardViewMenu = findViewById<CardView>(R.id.cardViewMenu)
        val cardViewAttender = findViewById<CardView>(R.id.cardViewAttender)
        val cardViewOrders = findViewById<CardView>(R.id.cardViewOrders)
        val btnLogout = findViewById<FloatingActionButton>(R.id.btnLogout)
        val sharedPreferences =
            applicationContext.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        val dbHelper = DatabaseHelper(this)

        cardViewMenu.setOnClickListener {
            intent = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent)
        }

        cardViewAttender.setOnClickListener {
            intent = Intent(this, ActivityAttenderList::class.java)
            startActivity(intent)
        }
        cardViewOrders.setOnClickListener {
            intent = Intent(this, ShowAllOrdersActivity::class.java)
            startActivity(intent)
        }


        btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("aname")
            editor.remove("pass")
            editor.apply()
            intent = Intent(this, ActivityAdmin::class.java)
            startActivity(intent)
            finish()
        }

    }
}