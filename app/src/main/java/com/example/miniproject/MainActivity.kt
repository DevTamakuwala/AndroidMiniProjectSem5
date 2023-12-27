package com.example.miniproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseHelper(this)
        val splashScreen = object : Thread() {
            override fun run(){
                try {
                    sleep(2000)
                }catch (e : InterruptedException){
                    e.printStackTrace()
                }finally {
                    startActivity(Intent(this@MainActivity,ActivityWelcome::class.java))
                    finish()
                }
            }
        }
        splashScreen.start()
    }
}