package com.okation.aivideocreator.ui.fragment.generatinglyrics


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.entitiy.ChatcptRequestBody
import com.okation.aivideocreator.data.entitiy.RapChatCptModel
import com.okation.aivideocreator.data.repo.RapRepository
import com.okation.aivideocreator.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratingLyricsViewModel @Inject constructor(var krepo : RapRepository): ViewModel()  {
    val rapResponse: MutableLiveData<RapChatCptModel> = MutableLiveData()

    fun getRapSong(body: ChatcptRequestBody){
        viewModelScope.launch {
            try{
                val response = ApiClient.getClient().sendText(body)
                if(response.isSuccessful){
                    rapResponse.postValue(response.body())
                }else{ }
            }catch (e : Exception){ }
        }
    }
}