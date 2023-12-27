package com.example.miniproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityAttender : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attender)

        val dbHelper = DatabaseHelper(this)
        val username = findViewById<EditText>(R.id.edUsername)
        val password = findViewById<EditText>(R.id.edPassword)
        val login = findViewById<Button>(R.id.btnLogin)

        val sharedPreferences =
            applicationContext.getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        var user = sharedPreferences.getString("uname", "")

        val check = dbHelper.checkUser(user!!)
        if (check != 1) {
            val editor = sharedPreferences.edit()
            editor.remove("uname")
            editor.remove("pass")
            editor.apply()
        }

        user = sharedPreferences.getString("uname", "")

        if (user!!.length > 0) {
            intent = Intent(this, ActivityAttenderHome::class.java)
            startActivity(intent)
            finish()
        }

        login.setOnClickListener {
            val res: Int = dbHelper.login(username.text.toString(), password.text.toString())
            if (res > 0) {
                val editor = sharedPreferences.edit()
                editor.putString("uname", username.text.toString())
                editor.putString("pwd", password.text.toString())
                editor.apply()
                intent = Intent(this, ActivityAttenderHome::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Wrong username or password..!!", Toast.LENGTH_LONG).show()
            }
        }

    }
}