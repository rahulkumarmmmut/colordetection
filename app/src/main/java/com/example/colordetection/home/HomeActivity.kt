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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.colordetection.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class HomeActivity : AppCompatActivity() {

    var uri: Uri? = null
    var bitmap: Bitmap? = null
    var colorName : String? = null
    private val _colorNameLiveData = MutableLiveData<String>()
    val colorNameLiveData: LiveData<String>
        get() = _colorNameLiveData


    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }

    private fun init(){
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        val generateButton: Button = findViewById(R.id.button)
        val cameraButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)


            cameraButton.setOnClickListener {
                ImagePicker.with(this)
                    .crop()
                    .maxResultSize(
                        1080,
                        1080
                    )    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
            }

        generateButton.setOnClickListener {
            if (bitmap != null) {

                ColorFinder(object : ColorFinder.CallbackInterface {
                    override fun onCompleted(color: String) {
                        getColorStringFromHex(color.substring(1))
                    }
                }).findDominantColor(bitmap)
            }

        }

    }

    fun setObservers(){
        var textView: TextView = findViewById(R.id.resultTextView)

        colorNameLiveData.observeForever {
            textView.visibility = View.VISIBLE
            textView.text= it
        }

    }

    fun getColorStringFromHex(color1: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecolorapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val colorApiService = retrofit.create(ColorApiService::class.java)

        colorApiService.getColorNameFromHex("$color1").enqueue(object : Callback<ColorApiResponse> {
            override fun onResponse(call: Call<ColorApiResponse>, response: Response<ColorApiResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.name?.value
                    _colorNameLiveData.value = data
                    Log.e("ColorApi", "got color name: $colorName ${response.code()} ${response.message()}")
                } else {
                    Log.e("ColorApi", "Failed to get color name: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ColorApiResponse>, t: Throwable) {
                Log.e("ColorApi", "Failed to get color name", t)
            }
        })
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

    interface ColorApiService {
        @GET("/id")
        fun getColorNameFromHex(
            @Query("hex") hex: String
        ): Call<ColorApiResponse>
    }
}