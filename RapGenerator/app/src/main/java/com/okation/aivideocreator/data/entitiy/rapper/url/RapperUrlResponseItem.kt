package com.okation.aivideocreator.data.entitiy.rapper.url


import com.google.gson.annotations.SerializedName

data class RapperUrlResponseItem(
    @SerializedName("transcription")
    val transcription: String?,
    @SerializedName("url")
    val url: String?
)