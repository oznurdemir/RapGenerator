package com.okation.aivideocreator.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.okation.aivideocreator.data.Song

@Database(entities = [Song::class], version = 4)
abstract class SongDatabase : RoomDatabase(){
    abstract val songDao: SongDao
}