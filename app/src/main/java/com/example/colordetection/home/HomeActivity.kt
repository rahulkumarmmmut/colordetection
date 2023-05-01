package com.example.colordetection.home

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.colordetection.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton



class HomeActivity : AppCompatActivity() {

    var uri: Uri? = null
    var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }

    private fun init(){
        setListeners()
    }

    private fun setListeners() {
        val generateButton: Button = findViewById(R.id.button)
        val cameraButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        val textView: TextView = findViewById(R.id.resultTextView)

        generateButton.setOnClickListener {
            if (bitmap != null) {
//                textView.visibility = View.VISIBLE
//                textView.setText("ORANGE")
//                ImageClassifier(uri)
               // ImageClassifier.result
                // setText(result)

                ColorFinder(object : ColorFinder.CallbackInterface {
                    override fun onCompleted(color: String) {
                        textView.visibility = View.VISIBLE
                        val color1: Int = Color.parseColor(color)
                        var colorName : String = getColorStringFromHex(color1);
                        textView.setText(""+color)
                    }
                }).findDominantColor(bitmap)
            }

            cameraButton.setOnClickListener {
                ImagePicker.with(this)
                    .crop()

                    .maxResultSize(
                        1080,
                        1080
                    )    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
            }


        }
    }

    fun getColorStringFromHex(color1: String) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            uri= data?.data!!
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

            var imageView: ImageView = findViewById(R.id.imageView)
            // Use Uri object instead of File to avoid storage permissions
            imageView.setImageBitmap(bitmap)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}