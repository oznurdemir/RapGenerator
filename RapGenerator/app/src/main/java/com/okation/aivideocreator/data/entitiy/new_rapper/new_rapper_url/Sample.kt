package com.okation.aivideocreator.data.entitiy.new_rapper.new_rapper_url


import com.google.gson.annotations.SerializedName

data class Sample(
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("url")
    val url: String?
)