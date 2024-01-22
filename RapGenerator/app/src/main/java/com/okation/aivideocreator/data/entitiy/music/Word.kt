package com.okation.aivideocreator.data.entitiy.music


import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("end")
    val end: Int?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("word")
    val word: String?
)