package com.okation.aivideocreator.ui.fragment.home_song_creator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.data.repo.RapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSongCreatorViewModel @Inject constructor(var krepo : RapRepository) : ViewModel(){
    private val _songCount = MutableLiveData<Int>()
    val songCount: LiveData<Int>
        get() = _songCount

    init {
        getSongCount()
       getSong()
    }
    fun getSong() = krepo.getSong()

    fun deleteSong(song: Song) {
        viewModelScope.launch {
            krepo.deleteSong(song)
        }
    }
    fun getSongCount() {
        viewModelScope.launch {
            _songCount.value = krepo.getSongCount()
        }
    }
}