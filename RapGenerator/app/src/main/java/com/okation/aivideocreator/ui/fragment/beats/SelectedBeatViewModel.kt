package com.okation.aivideocreator.ui.fragment.beats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.entitiy.beat.BackingTrack
import com.okation.aivideocreator.data.entitiy.beat.url.BeatUrlResponse
import com.okation.aivideocreator.data.repo.RapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SelectedBeatViewModel @Inject constructor(var krepo : RapRepository): ViewModel() {
    val  beatUuid = MutableLiveData<List<BackingTrack>>()
    val  beatUrl = MutableLiveData<BeatUrlResponse>()
    init {
        showBeat()
    }

    private fun showBeat() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){

            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                beatUuid.value = krepo.showBeat() as List<BackingTrack>?
            }catch (e : Exception){ }
        }
    }

    fun showBeatUrl(beatId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                beatUrl.value = krepo.getBeatUrl(beatId)
            } catch (e: Exception) { }
        }
    }
}