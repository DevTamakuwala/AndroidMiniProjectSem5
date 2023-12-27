package com.example.miniproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val sharedPreferences = applicationContext.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        

        val User = sharedPreferences.getString("aname","")
        if (User!!.length>0){
            intent = Intent(this,AdminOptionActivity::class.java)
            startActivity(intent)
            finish()
        }

        var dbHelper = DatabaseHelper(this)
        var username = findViewById<EditText>(R.id.edUsername)
        var password = findViewById<EditText>(R.id.edPassword)
        var login = findViewById<Button>(R.id.btnLogin)

        login.setOnClickListener {
            var res: Int = dbHelper.admin(username.text.toString(), password.text.toString())

            val editor = sharedPreferences.edit()
            editor.putString("aname", username.text.toString())
            editor.putString("pass", password.text.toString())
            val success = editor.commit()

            if (res > 0) {
                intent = Intent(this, AdminOptionActivity::class.java)
                startActivity(intent)
                username.text.clear()
                password.text.clear()
                password.clearFocus()
                username.clearFocus()
                finish()
            } else {
                Toast.makeText(this, "Wrong username or password..!!", Toast.LENGTH_LONG).show()
            }
        }

    }
}