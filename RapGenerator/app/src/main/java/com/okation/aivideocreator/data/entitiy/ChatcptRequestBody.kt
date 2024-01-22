package com.okation.aivideocreator.data.entitiy

data class ChatcptRequestBody(
    val model: String = "gpt-3.5-turbo-instruct",
    val prompt:String,
    val temperature: Int = 1,
    val max_tokens: Int = 250,
   )