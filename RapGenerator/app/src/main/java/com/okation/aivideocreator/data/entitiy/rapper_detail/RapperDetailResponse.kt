package com.okation.aivideocreator.data.entitiy.rapper_detail


import com.google.gson.annotations.SerializedName

data class RapperDetailResponse(
    @SerializedName("accent")
    val accent: Any?,
    @SerializedName("added_at")
    val addedAt: Any?,
    @SerializedName("age")
    val age: Any?,
    @SerializedName("architecture")
    val architecture: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("contributors")
    val contributors: Any?,
    @SerializedName("controls")
    val controls: Any?,
    @SerializedName("description")
    val description: Any?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("features")
    val features: List<String?>?,
    @SerializedName("gender")
    val gender: Any?,
    @SerializedName("hifi_gan_vocoder")
    val hifiGanVocoder: Any?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("images")
    val images: Any?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("is_primary")
    val isPrimary: Any?,
    @SerializedName("is_private")
    val isPrivate: Boolean?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("memberships")
    val memberships: Any?,
    @SerializedName("ml_model_id")
    val mlModelİd: Any?,
    @SerializedName("mood")
    val mood: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("samples")
    val samples: List<Any?>?,
    @SerializedName("speaker_id")
    val speakerİd: Any?,
    @SerializedName("style")
    val style: Any?,
    @SerializedName("symbol_set")
    val symbolSet: String?,
    @SerializedName("tags")
    val tags: Any?,
    @SerializedName("voice_actor")
    val voiceActor: String?,
    @SerializedName("voicemodel_uuid")
    val voicemodelUuid: String?
)