package com.okation.aivideocreator.data.entitiy.beat


import com.google.gson.annotations.SerializedName

data class BeatResponse(
    @SerializedName("backing_tracks")
    val backingTracks: List<BackingTrack?>?
)