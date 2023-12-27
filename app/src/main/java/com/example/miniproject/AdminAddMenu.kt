package com.example.miniproject

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.String

@Suppress("DEPRECATION")
class AdminAddMenu : AppCompatActivity() {
    internal var dbHelper = DatabaseHelper(this)
    private val GALLERY_PERMISSION_CODE = 101
    private val SELECT_IMAGE_REQUEST_CODE = 201
    private lateinit var img: ImageView
    private lateinit var spCat: Spinner
    private var imagePath: ByteArray? =null


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_menu)

        img = findViewById(R.id.img)
        val btnAddPhoto = findViewById<Button>(R.id.btnAddPhoto)
        val btnAddItem = findViewById<Button>(R.id.btnAdItem)
        val edProductName = findViewById<EditText>(R.id.edProductName)
        val edPrice = findViewById<EditText>(R.id.edPrice)
        val edDesc = findViewById<EditText>(R.id.edDesc)

        val dbHelper = DatabaseHelper(this)
        spCat = findViewById(R.id.spCat)

        btnAddItem.setOnClickListener {
            val category :String = spCat.selectedItem.toString()
            val res = dbHelper.addMenu(edProductName.text.toString(),Integer.parseInt(edPrice.text.toString()),category, edDesc.text.toString(), imagePath)
            if (res > 0){
                intent = Intent(this,AdminMenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btnAddPhoto.setOnClickListener {
            checkGalleryPermission()
        }

        val res = dbHelper.showCategory()

        if (res!!.count > 0){
            val list1 = arrayListOf<String>()
            while (res.moveToNext()) {
                val row1 = res.getString(1)
                list1.add(row1)
            }
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list1)
            spCat.adapter = arrayAdapter
        }
        else{
            intent = Intent(this,AdminAddCategory::class.java)
            startActivity(intent)
        }

    }

    private fun checkGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                GALLERY_PERMISSION_CODE
            )
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GALLERY_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SELECT_IMAGE_REQUEST_CODE -> {
                    val imageUri = data?.data
                    val imageStream = contentResolver.openInputStream(imageUri!!)
                    val imageBitmap = BitmapFactory.decodeStream(imageStream)
                    imagePath= bitmapToByteArray(imageBitmap)
                    img.setImageBitmap(imageBitmap)
                    saveImageToGallery(imageBitmap)
                }
            }
        }
    }

    fun bitmapToByteArray(bitmap: Bitmap, format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(format, quality, outputStream)
        return outputStream.toByteArray()
    }
    private fun saveImageToGallery(bitmap: Bitmap) {
        val fileName = "image_${System.currentTimeMillis()}.jpg"
        val outputStream: OutputStream
        try {
            val imageFile = File(getExternalFilesDir(null), fileName)
            outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // Add the image to the device's gallery
            MediaStore.Images.Media.insertImage(
                contentResolver,
                imageFile.absolutePath,
                fileName,
                "Image saved from ImageApp"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}