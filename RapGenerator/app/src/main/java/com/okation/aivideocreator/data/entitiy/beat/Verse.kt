package com.okation.aivideocreator.data.entitiy.beat


import com.google.gson.annotations.SerializedName

data class Verse(
    @SerializedName("label")
    val label: String?,
    @SerializedName("length_in_measures")
    val lengthİnMeasures: Int?,
    @SerializedName("start")
    val start: Double?
)