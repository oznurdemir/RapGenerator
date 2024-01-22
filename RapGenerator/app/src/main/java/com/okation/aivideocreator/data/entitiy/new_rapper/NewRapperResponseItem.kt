package com.okation.aivideocreator.data.entitiy.new_rapper


import com.google.gson.annotations.SerializedName

data class NewRapperResponseItem(
    @SerializedName("accent")
    val accent: String?,
    @SerializedName("added_at")
    val addedAt: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("architecture")
    val architecture: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("contributors")
    val contributors: List<String?>?,
    @SerializedName("controls")
    val controls: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hifi_gan_vocoder")
    val hifiGanVocoder: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("is_primary")
    val isPrimary: Boolean?,
    @SerializedName("is_private")
    val isPrivate: Boolean?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("memberships")
    val memberships: List<Membership?>?,
    @SerializedName("ml_model_id")
    val mlModelİd: Int?,
    @SerializedName("mood")
    val mood: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("samples")
    val samples: List<Sample?>?,
    @SerializedName("speaker_id")
    val speakerİd: Int?,
    @SerializedName("style")
    val style: String?,
    @SerializedName("symbol_set")
    val symbolSet: String?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("voice_actor")
    val voiceActor: String?,
    @SerializedName("voicemodel_uuid")
    val voicemodelUuid: String?
)