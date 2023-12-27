package com.example.miniproject

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityOrderNow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_now)

        val ID: String = intent.extras?.getString("ID").toString()
        val QTY = arrayOf<Any?>("--Select quantity--","1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val tblNo = arrayOf("--Select Table Number--","1", "2", "3", "4", "5")
        val Pname = findViewById<TextView>(R.id.pname)
        val Price = findViewById<TextView>(R.id.price)
        val img = findViewById<ImageView>(R.id.img)
        val qty = findViewById<Spinner>(R.id.qty)
        val tblno = findViewById<Spinner>(R.id.tblNo)
        val btnBuyNow = findViewById<Button>(R.id.btnBuyNow)
        val dbHelper = DatabaseHelper(this)

        val res = dbHelper.buyNow(ID)

        var name: String
        var price: String
        var Img: ByteArray

        while (res!!.moveToNext()) {
            name = res.getString(1)
            price = res.getString(2)
            Img = res.getBlob(5)

            img.setImageBitmap(BitmapFactory.decodeByteArray(Img, 0, Img.size))
            Pname.text = name
            Price.text = price
        }

        val adapter = ArrayAdapter<Any?>(this, R.layout.spinner_list, QTY)
        adapter.setDropDownViewResource(R.layout.spinner_list)
        qty.adapter = adapter

        val adapterTbl = ArrayAdapter<Any?>(this, R.layout.spinner_list, tblNo)
        adapterTbl.setDropDownViewResource(R.layout.spinner_list)
        tblno.adapter = adapterTbl

        val p = Integer.parseInt(Price.text.toString())
        var Qty = ""
        var tbl = ""

        val spinnerItemChangeListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Qty = qty.selectedItem.toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val spinnerItemChangeListener2 = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tbl = tblno.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        qty.onItemSelectedListener = spinnerItemChangeListener
        tblno.onItemSelectedListener = spinnerItemChangeListener2

        btnBuyNow.setOnClickListener {
            if (Qty == "--Select quantity--" || tbl =="--Select Table Number--"){
                Toast.makeText(this, "Select Quentity & table number", Toast.LENGTH_SHORT).show()
            }else{
                Price.text = (p * Integer.parseInt(qty.selectedItem.toString())).toString()
                dbHelper.order(Pname.text.toString(),Qty,tbl,Price.text.toString())
                intent = Intent(this,AdminOrdersActivity::class.java)
                startActivity(intent)
            }
        }
    }
}