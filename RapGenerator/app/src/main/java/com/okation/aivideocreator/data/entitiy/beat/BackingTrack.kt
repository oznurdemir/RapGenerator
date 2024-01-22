package com.okation.aivideocreator.data.entitiy.beat


import com.google.gson.annotations.SerializedName

data class BackingTrack(
    @SerializedName("bpm")
    val bpm: Double?,
    @SerializedName("bucket")
    val bucket: String?,
    @SerializedName("is_public")
    val isPublic: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("verses")
    val verses: List<Verse?>?
)