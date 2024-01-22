package com.okation.aivideocreator.ui.fragment.generating_song

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.entitiy.music.MusicRequestBody
import com.okation.aivideocreator.data.entitiy.music.MusicResponse
import com.okation.aivideocreator.data.repo.RapRepository
import com.okation.aivideocreator.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratingSongViewModel @Inject constructor(var krepo : RapRepository): ViewModel() {
    val musicResponse: MutableLiveData<MusicResponse> = MutableLiveData()

    fun getRapSong(body: MusicRequestBody){
        viewModelScope.launch {
            try{
                val response = ApiClient.getClientMusic().sendMusic(body)
                if(response.isSuccessful){
                    musicResponse.postValue(response.body())
                }else{ }
            }catch (e : Exception){
            }
        }
    }
}