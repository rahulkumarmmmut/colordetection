package com.example.colordetection.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.colordetection.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    var uri: Uri? = null



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
        val textView: TextView = findViewById(R.id.textView)

        generateButton.setOnClickListener {
            if (uri != null) {
                textView.visibility = View.VISIBLE

                textView.setText("ORANGE")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            uri= data?.data!!
            var imageView: ImageView = findViewById(R.id.imageView)
            // Use Uri object instead of File to avoid storage permissions
            imageView.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}