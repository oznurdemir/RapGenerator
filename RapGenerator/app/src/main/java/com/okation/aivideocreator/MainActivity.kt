package com.okation.aivideocreator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.okation.aivideocreator.ui.fragment.payment.PaymentViewModel
import com.okation.aivideocreator.util.Constants
import com.revenuecat.purchases.LogLevel
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : PaymentViewModel by viewModels()
    private val navController : NavController by lazy {
        findNavController(R.id.mainNavBar)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.initializePremiumStatus(applicationContext)
        viewModel.isPremium.observe(this) { isPremium ->
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    Log.d("premium",isPremium.toString())
                    if (isPremium == true && destination.id == R.id.firstPageFragment) {
                        navController.navigate(R.id.action_firstPageFragment_to_homePageFragment)
                }
            }
        }

        Purchases.logLevel = LogLevel.DEBUG
        Purchases.configure(PurchasesConfiguration.Builder(this, Constants.GOOGLE_API_KEY).build())
        WindowCompat.setDecorFitsSystemWindows(window,false)
    }

    fun setFullscreenFlags() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
    }
    fun changeFullScreenFlags(isFullSize : Boolean){
        if (isFullSize)   window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )else window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
}