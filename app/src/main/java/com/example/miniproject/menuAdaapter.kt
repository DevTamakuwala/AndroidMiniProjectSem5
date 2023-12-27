package com.example.miniproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class menuAdaapter(
    private val context: Context,
    private var MenuModel: List<menuModel>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var PName: TextView
    private lateinit var PID: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDesc: TextView
    private lateinit var img1: ImageView
    val dbHelper = DatabaseHelper(context)

    override fun getCount(): Int {
        return MenuModel.size
    }

    override fun getItem(position: Int): menuModel {
        return MenuModel[position]
    }

    override fun getItemId(position: Int): Long {
        return MenuModel[position].productId.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.menu_layout, null)
        }
        img1 = convertView!!.findViewById(R.id.imgLogo)
        val btnUpdate = convertView.findViewById<Button>(R.id.upd_btn)
        val btnDelete = convertView.findViewById<Button>(R.id.delete_btn)
        PID = convertView.findViewById(R.id.tvID)
        PName = convertView.findViewById(R.id.tvName)
        tvPrice = convertView.findViewById(R.id.tvPrice)
        tvDesc = convertView.findViewById(R.id.tvDesc)

        val listItem = getItem(position) as menuModel

        val img = listItem.img

        img1.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.size))
        PName.text = MenuModel[position].productName
        tvPrice.text = MenuModel[position].price + " \u20B9"
        tvDesc.text = MenuModel[position].desc
        PID.text = MenuModel[position].productId.toString()

        btnUpdate.setOnClickListener {
            val intent = Intent(context, AdminUpdateActivity::class.java)
            intent.putExtra("id", MenuModel[position].productId.toString())
            intent.putExtra("name", MenuModel[position].productName)
            intent.putExtra("desc", MenuModel[position].desc)
            intent.putExtra("price", MenuModel[position].price)
            convertView.context.startActivity(intent)
        }

        btnDelete.setOnClickListener {
            dbHelper.deleteItem(MenuModel[position].productId.toString())
            refreshData(dbHelper.showMenu())
        }
        return convertView
    }

    private fun refreshData(newmyData: List<menuModel>) {
        MenuModel = newmyData
        notifyDataSetChanged()
    }
}