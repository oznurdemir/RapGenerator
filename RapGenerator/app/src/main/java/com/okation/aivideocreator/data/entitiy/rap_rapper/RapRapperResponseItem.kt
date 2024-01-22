package com.okation.aivideocreator.data.entitiy.rap_rapper


import com.google.gson.annotations.SerializedName

data class RapRapperResponseItem(
    @SerializedName("category")
    val category: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("is_private")
    val isPrivate: Boolean?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("voicemodel_uuid")
    val voicemodelUuid: String?
)