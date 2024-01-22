package com.okation.aivideocreator.data.entitiy.music


import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("bpm")
    val bpm: Double?,
    @SerializedName("lines")
    val lines: List<Line?>?,
    @SerializedName("mix_url")
    val mixUrl: String?,
    @SerializedName("render_uuid")
    val renderUuid: String?,
    @SerializedName("render_video_response")
    val renderVideoResponse: Any?,
    @SerializedName("timestamps")
    val timestamps: Any?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vocals_url")
    val vocalsUrl: String?
)