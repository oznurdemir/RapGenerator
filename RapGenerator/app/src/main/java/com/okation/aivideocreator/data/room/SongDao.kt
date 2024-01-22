package com.okation.aivideocreator.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okation.aivideocreator.data.Song

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSong(songEntitiy : Song)

    @Query("SELECT * FROM song ORDER BY  id DESC" )
    fun getSong() : LiveData<List<Song>>

    @Delete
    suspend fun deleteSong(song: Song)

    @Query("SELECT COUNT(*) FROM song")
    suspend fun getSongCount(): Int
}