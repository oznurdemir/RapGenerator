package com.okation.aivideocreator.ui.fragment.payment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentViewModel : ViewModel() {
    private val _isPremium = MutableLiveData<Boolean>()
    val isPremium: LiveData<Boolean>
        get() = _isPremium

    fun setPremiumStatus(isPremium: Boolean) {
        _isPremium.value = isPremium
    }

    fun initializePremiumStatus(context: Context) {
        val sharedPreferences = context.getSharedPreferences("premium", Context.MODE_PRIVATE)
        val isPremium = sharedPreferences.getBoolean("is_premium", false)
        _isPremium.value = isPremium
    }
}
