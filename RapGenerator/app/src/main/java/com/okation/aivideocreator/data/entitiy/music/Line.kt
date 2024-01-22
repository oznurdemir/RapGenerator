package com.okation.aivideocreator.data.entitiy.music


import com.google.gson.annotations.SerializedName

data class Line(
    @SerializedName("end")
    val end: Int?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("words")
    val words: List<Word?>?
)