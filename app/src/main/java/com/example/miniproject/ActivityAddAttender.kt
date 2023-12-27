package com.example.miniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityAddAttender : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_attender)

        val dbHelper = DatabaseHelper(this)
        val username = findViewById<EditText>(R.id.edUsername)
        val password = findViewById<EditText>(R.id.edPassword)
        val login = findViewById<Button>(R.id.btnLogin)

        login.setOnClickListener {
            val res : Long = dbHelper.addUser(username.text.toString(),password.text.toString())
            if (res > 0){
                Toast.makeText(this,"Attender add Successfully..!!",Toast.LENGTH_LONG).show()
                intent = Intent(this,ActivityAttenderList::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Attender not added please try again..!!",Toast.LENGTH_LONG).show()
            }
        }

    }
}