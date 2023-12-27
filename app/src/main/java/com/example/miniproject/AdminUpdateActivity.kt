package com.example.miniproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_update)
        val tv = findViewById<TextView>(R.id.tvId)
        val Pname = findViewById<TextView>(R.id.edProductName)
        val price = findViewById<TextView>(R.id.edPrice)
        val desc = findViewById<TextView>(R.id.edDesc)
        val updItem = findViewById<Button>(R.id.btnUpdItem)
        val intent = intent
        val extras = intent.extras
        val id = extras?.getString("id")
        val name = extras?.getString("name")
        val Price = extras?.getString("price")
        val Desc = extras?.getString("desc")
        val dbHelper = DatabaseHelper(this)
        tv.text = id
        Pname.text = name
        price.text = Price
        desc.text = Desc
        updItem.setOnClickListener {
            dbHelper.updItem(
                Pname.text.toString(),
                tv.text.toString(),
                price.text.toString(),
                desc.text.toString()
            )
            val intent1 = Intent(this,AdminMenuActivity::class.java)
            startActivity(intent1)
            finish()
        }
    }
}