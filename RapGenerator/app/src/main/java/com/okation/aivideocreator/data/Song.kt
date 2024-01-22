package com.okation.aivideocreator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity("song")
data class Song (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    var rapperName : String,
    val img : Int,
    val songUrl : String): Serializable
