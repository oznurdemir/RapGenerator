package com.okation.aivideocreator.data.entitiy.beat.url


import com.google.gson.annotations.SerializedName

data class BackingTrack(
    @SerializedName("bpm")
    val bpm: Double?,
    @SerializedName("dbfs")
    val dbfs: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("timeline_origin")
    val timelineOrigin: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("verses")
    val verses: List<Verse?>
)