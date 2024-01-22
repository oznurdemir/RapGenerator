package com.okation.aivideocreator.data.entitiy.music

data class MusicRequestBody(
    val lyrics : List<List<String>>,
    val backing_track : String,
    val voicemodel_uuid : String
)
