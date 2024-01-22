package com.okation.aivideocreator.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.data.repo.RapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SongViewModel @Inject constructor(var krepo : RapRepository) : ViewModel(){
    fun addSong(song: Song) = viewModelScope.launch {
        krepo.addSong(song)
    }
}