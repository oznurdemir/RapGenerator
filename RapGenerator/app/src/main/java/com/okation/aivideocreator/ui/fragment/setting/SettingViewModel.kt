package com.okation.aivideocreator.ui.fragment.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okation.aivideocreator.data.entitiy.setting.SettingItem
import com.okation.aivideocreator.data.repo.RapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(var krepo : RapRepository): ViewModel() {
    var settingItemList = MutableLiveData<List<SettingItem>>()

    init {
        showSettingItem()
    }

    fun showSettingItem(){
        CoroutineScope(Dispatchers.Main).launch {
            settingItemList.value = krepo.showSettingsItem()
        }
    }
}