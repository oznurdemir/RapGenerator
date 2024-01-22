package com.okation.aivideocreator.data.datasource

import android.content.Context
import androidx.lifecycle.LiveData
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.data.Title
import com.okation.aivideocreator.data.entitiy.beat.BackingTrack
import com.okation.aivideocreator.data.entitiy.beat.url.BeatUrlResponse
import com.okation.aivideocreator.data.entitiy.rap_rapper.RapRapperResponseItem
import com.okation.aivideocreator.data.entitiy.rapper.url.RapperUrlResponseItem
import com.okation.aivideocreator.data.entitiy.setting.SettingItem
import com.okation.aivideocreator.data.room.SongDatabase
import com.okation.aivideocreator.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RapDataSource(val context: Context, val beatApiService: ApiService, private val db: SongDatabase){
    suspend fun showTitle() : List<Title> =
        withContext(Dispatchers.IO){
            val titleList = ArrayList<Title>()
            val title1 = Title(1,"Fun")
            val title2 = Title(2,"Happy")
            val title3 = Title(3,"Love")
            val title4 = Title(4,"Sad")
            val title5 = Title(5,"Sexy")
            titleList.add(title1)
            titleList.add(title2)
            titleList.add(title3)
            titleList.add(title4)
            titleList.add(title5)
            return@withContext titleList
        }
    suspend fun showContents(titleId : Int) : List<String> =
        withContext(Dispatchers.IO){
            val list = ArrayList<String>()
            when (titleId) {
                1 -> {
                    list.add(context.resources.getString(R.string.`fun`))
                    list.add(context.resources.getString(R.string.`fun`))
                    list.add(context.resources.getString(R.string.`fun`))
                    list.add(context.resources.getString(R.string.`fun`))
                }
                2 -> {
                    list.add(context.resources.getString(R.string.`happy`))
                    list.add(context.resources.getString(R.string.`happy`))
                    list.add(context.resources.getString(R.string.`happy`))
                    list.add(context.resources.getString(R.string.`happy`))
                }
                3 -> {
                    list.add(context.resources.getString(R.string.`love`))
                    list.add(context.resources.getString(R.string.`love`))
                    list.add(context.resources.getString(R.string.`love`))
                    list.add(context.resources.getString(R.string.`love`))
                }
                4 -> {
                    list.add(context.resources.getString(R.string.`sad`))
                    list.add(context.resources.getString(R.string.`sad`))
                    list.add(context.resources.getString(R.string.`sad`))
                    list.add(context.resources.getString(R.string.`sad`))
                }
                5 -> {
                    list.add(context.resources.getString(R.string.`sexy`))
                    list.add(context.resources.getString(R.string.`sexy`))
                    list.add(context.resources.getString(R.string.`sexy`))
                    list.add(context.resources.getString(R.string.`sexy`))
                }
            }
            return@withContext list
        }
    suspend fun showSettingsItem() : List<SettingItem> =
        withContext(Dispatchers.IO){
            val itemList = ArrayList<SettingItem>()
            val item1 = SettingItem(1,context.resources.getString(R.string.terms_of_use))
            val item2 = SettingItem(2,context.resources.getString(R.string.contact_us))
            val item3 = SettingItem(3,context.resources.getString(R.string.privacy_policy))
            val item4 = SettingItem(4,context.resources.getString(R.string.restore_purchase))
            val item5 = SettingItem(5,context.resources.getString(R.string.help))
            itemList.add(item1)
            itemList.add(item2)
            itemList.add(item3)
            itemList.add(item4)
            itemList.add(item5)
            return@withContext itemList
        }
    suspend fun showBeat() : List<BackingTrack?>? =
        withContext(Dispatchers.IO){
                return@withContext beatApiService.getBeat().backingTracks
        }
    suspend fun getBeatUrl(beatId: String): BeatUrlResponse =
        withContext(Dispatchers.IO) {
            return@withContext beatApiService.getBeatUrl(beatId)
        }
    suspend fun showRapper() : List<RapRapperResponseItem> =
        withContext(Dispatchers.IO){
            return@withContext beatApiService.getRapper()
        }
    suspend fun getRapperUrl(rapperId: String): List<RapperUrlResponseItem> =
        withContext(Dispatchers.IO) {
            return@withContext beatApiService.getRapperUrl(rapperId)
        }
    suspend fun addSong(kingPeopleEntity: Song) {
        db.songDao.addSong(kingPeopleEntity)
    }
    fun getSong(): LiveData<List<Song>> {
        return db.songDao.getSong()
    }
    suspend fun deleteSong(song: Song) {
        db.songDao.deleteSong(song)
    }
    suspend fun getSongCount(): Int {
        return db.songDao.getSongCount()
    }
}