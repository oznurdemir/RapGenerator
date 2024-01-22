package com.okation.aivideocreator.data.repo

import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.data.Title
import com.okation.aivideocreator.data.datasource.RapDataSource
import com.okation.aivideocreator.data.entitiy.beat.BackingTrack
import com.okation.aivideocreator.data.entitiy.beat.url.BeatUrlResponse
import com.okation.aivideocreator.data.entitiy.rap_rapper.RapRapperResponseItem
import com.okation.aivideocreator.data.entitiy.rapper.url.RapperUrlResponseItem
import com.okation.aivideocreator.data.entitiy.setting.SettingItem

class RapRepository (var kds: RapDataSource){
    suspend fun showTitle() : List<Title> = kds.showTitle()
    suspend fun showContents(id : Int) : List<String> = kds.showContents(id)
    suspend fun showSettingsItem() : List<SettingItem> = kds.showSettingsItem()
    suspend fun showBeat() : List<BackingTrack?>? = kds.showBeat()
    suspend fun getBeatUrl(beatId: String) : BeatUrlResponse = kds.getBeatUrl(beatId)
    suspend fun showRapper() : List<RapRapperResponseItem> = kds.showRapper()
    suspend fun getRapperUrl(rapperId: String) : List<RapperUrlResponseItem> = kds.getRapperUrl(rapperId)
    suspend fun addSong(song: Song) = kds.addSong(song)
    fun getSong() = kds.getSong()
    suspend fun deleteSong(song: Song) {
        kds.deleteSong(song)
    }
    suspend fun getSongCount() : Int = kds.getSongCount()
}