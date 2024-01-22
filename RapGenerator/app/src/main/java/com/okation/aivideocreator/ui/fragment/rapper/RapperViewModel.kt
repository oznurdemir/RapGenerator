package com.okation.aivideocreator.ui.fragment.rapper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okation.aivideocreator.data.entitiy.rap_rapper.RapRapperResponseItem
import com.okation.aivideocreator.data.entitiy.rapper.url.RapperUrlResponseItem
import com.okation.aivideocreator.data.repo.RapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RapperViewModel @Inject constructor(var krepo : RapRepository): ViewModel() {
    val rapper = MutableLiveData<List<RapRapperResponseItem>>()
    var  rapperUrl = MutableLiveData<List<RapperUrlResponseItem>>()

    init {
        showRapper()
    }

    private fun showRapper() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                rapper.value = krepo.showRapper()
            }catch (e : Exception){ }
        }
    }

    fun showRapperUrl(rapperId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                rapperUrl.value = krepo.getRapperUrl(rapperId)
            } catch (e: Exception) { }
        }
    }
}