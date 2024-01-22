package com.okation.aivideocreator.data.entitiy.new_rapper


import com.google.gson.annotations.SerializedName

data class Sample(
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("url")
    val url: String?
)