package com.okation.aivideocreator.ui.shared_viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val  rap = MutableLiveData<String>()
    val beatUrl = MutableLiveData<String>()
    val rapperUrl = MutableLiveData<String>()
    val rapperImageResId = MutableLiveData<Int>()

    fun saveRap(newRap : String){
        rap.value = newRap
    }

    fun savebeatUrl(newBeatUrl : String){
        beatUrl.value = newBeatUrl
    }

    fun saverapperUrl(newRapperUrl : String){
        rapperUrl.value = newRapperUrl
    }

    fun saveRapperImageResId(imageResId: Int) {
        rapperImageResId.value = imageResId
    }
}