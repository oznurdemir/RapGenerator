package com.okation.aivideocreator.data.entitiy.beat.url


import com.google.gson.annotations.SerializedName

data class BeatUrlResponse(
    @SerializedName("backing_track")
    val backingTrack: BackingTrack?
)