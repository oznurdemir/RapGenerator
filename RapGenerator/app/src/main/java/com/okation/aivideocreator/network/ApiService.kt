package com.okation.aivideocreator.network

import com.okation.aivideocreator.data.entitiy.ChatcptRequestBody
import com.okation.aivideocreator.data.entitiy.RapChatCptModel
import com.okation.aivideocreator.data.entitiy.beat.BeatResponse
import com.okation.aivideocreator.data.entitiy.beat.url.BeatUrlResponse
import com.okation.aivideocreator.data.entitiy.music.MusicRequestBody
import com.okation.aivideocreator.data.entitiy.music.MusicResponse
import com.okation.aivideocreator.data.entitiy.rap_rapper.RapRapperResponse
import com.okation.aivideocreator.data.entitiy.rapper.url.RapperUrlResponseItem
import com.okation.aivideocreator.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Authorization: Bearer ${Constants.API_KEY}")
    @POST("completions")
    suspend fun sendText(@Body text: ChatcptRequestBody) : Response<RapChatCptModel>

    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    @GET("${Constants.END_POINT_BEAT}")
    suspend fun getBeat() : BeatResponse

    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    @GET("${Constants.END_POINT_BEAT_URL}/{uuid}")
    suspend fun getBeatUrl(@Path("uuid") body: String): BeatUrlResponse

    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    @GET("${Constants.END_POINT_RAPPER}")
    suspend fun getRapper() : RapRapperResponse

    @Headers("Authorization: Basic ${Constants.API_KEY_BEAT}")
    @GET("${Constants.END_POINT_RAPPER}/{voicemodel_uuid}/samples")
    suspend fun getRapperUrl(@Path("voicemodel_uuid") body: String): List<RapperUrlResponseItem>

    @Headers("Authorization: Basic ${Constants.API_KEY_MUSIC}")
    @POST("${Constants.END_POINT_MUSIC}")
    suspend fun sendMusic(@Body music: MusicRequestBody): Response<MusicResponse>

}