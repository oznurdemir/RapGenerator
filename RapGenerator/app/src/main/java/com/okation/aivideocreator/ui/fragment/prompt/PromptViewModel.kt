package com.okation.aivideocreator.ui.fragment.prompt

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okation.aivideocreator.data.Title
import com.okation.aivideocreator.data.repo.RapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromptViewModel  @Inject constructor(var krepo : RapRepository, var context: Context): ViewModel() {
    var titleList = MutableLiveData<List<Title>>()
    var contentsList = MutableLiveData<List<String>>()

    init {
        showTitle()
        showContents(1)
    }

    fun showTitle(){
        CoroutineScope(Dispatchers.Main).launch {
            titleList.value = krepo.showTitle()
        }
    }

    fun showContents(id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            contentsList.value = krepo.showContents(id)
        }
    }
}