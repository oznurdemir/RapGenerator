package com.okation.aivideocreator.data.entitiy.new_rapper


import com.google.gson.annotations.SerializedName

data class Membership(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)