package com.example.colordetection.home
import com.google.gson.annotations.SerializedName


data class ColorApiResponse (

    @SerializedName("name")
    val name: ColorName,

    @SerializedName("hex")
    val hex: ColorHex,

)

data class ColorName(
    @SerializedName("value") val value: String
)

data class ColorHex(
    @SerializedName("value") val value: String,
    @SerializedName("clean") val clean: String
)

