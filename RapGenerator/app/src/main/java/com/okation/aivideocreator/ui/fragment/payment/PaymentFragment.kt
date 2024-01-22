package com.okation.aivideocreator.ui.fragment.payment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.okation.aivideocreator.MainActivity
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentPaymentBinding
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.PurchaseParams
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.purchaseWith
import com.okation.aivideocreator.util.Constants
class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    lateinit var packAge: Package
    val pacList = mutableListOf<Package>()
    private val viewModel: PaymentViewModel by viewModels()
    private lateinit var onboardingButtonDrawable: Drawable
    private lateinit var defaultBackgroundDrawable: Drawable
    private lateinit var premiumCardDravable: Drawable

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        onboardingButtonDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.onboarding_button)!!
        defaultBackgroundDrawable = binding.cardViewWeek.background
        premiumCardDravable =  ContextCompat.getDrawable(requireContext(), R.drawable.premium_card_selected)!!



        binding.cardViewWeek.setOnClickListener {
            updateTextColors(
                selectedCard = binding.cardViewWeek,
                selectedText = binding.txtWeekly,
                selectedTextDollar = binding.txtWeeklyDollar
            )
            packAge = pacList[0]
            binding.textViewRemiumTitle.setText(R.string.get_premium)
            binding.txtPremiumItem.setText(R.string.elevate_you)
        }

        binding.cardViewMonth.setOnClickListener {
            updateTextColors(
                selectedCard = binding.cardViewMonth,
                selectedText = binding.txtWeek,
                selectedTextDollar = binding.txtWeekDollar
            )
            packAge = pacList[1]
            binding.textViewRemiumTitle.setText(R.string.get_premium)
            binding.txtPremiumItem.setText(R.string.elevate_you)
        }

        binding.cardViewAnnual.setOnClickListener {
            updateTextColors(
                selectedCard = binding.cardViewAnnual,
                selectedText = binding.txtAnnual,
                selectedTextDollar = binding.txtAnnualDollar
            )
            packAge = pacList[2]
            binding.textViewRemiumTitle.setText(R.string.get_premium)
            binding.txtPremiumItem.setText(R.string.elevate_you)
        }

        binding.imageViewClose.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_paymentFragment_to_homePageFragment)
        }

        Purchases.sharedInstance.getOfferingsWith({
        }) { offerings ->
            offerings.current?.availablePackages?.forEach {
                pacList.add(it)
            }
            offerings.current?.getPackage("Small")?.also {
                binding.txtWeeklyDollar.text = it.product.price.formatted
            }
            offerings.current?.getPackage("Medium")?.also {
                binding.txtWeekDollar.text = it.product.price.formatted
            }
            offerings.current?.getPackage("Large")?.also {
                binding.txtAnnualDollar.text = it.product.price.formatted
            }
        }
        binding.buttonPremium.setOnClickListener {
            setButtonEnabled(false)
            Purchases.sharedInstance.purchaseWith(
                PurchaseParams.Builder(requireActivity(), packAge).build(),
                onError = { error, _ ->
                    setButtonEnabled(true)
                    Log.d("error",error.message.toString())
                },
                onSuccess = { _, _ ->
                    viewModel.setPremiumStatus(true)

                    val sharedPreferences =
                        requireContext().getSharedPreferences("premium", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_premium", true)
                    editor.apply()
                    findNavController().navigate(R.id.action_paymentFragment_to_homePageFragment)
                }
            )
        }

        binding.apply {
            txtTermOfUse.setOnClickListener {
                openUrl("${Constants.NEON_URL}")
            }
            txtRestorePurchare.setOnClickListener {
                openUrl("${Constants.NEON_URL}")
            }
            txtPrivacyPolicy.setOnClickListener {
                openUrl("${Constants.NEON_URL}")
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    private fun updateTextColors(
        selectedCard: View,
        selectedText: TextView,
        selectedTextDollar: TextView
    ) {
        resetTextColors()

        selectedCard.background = premiumCardDravable
        selectedText.setTextColor(ContextCompat.getColor(requireContext(), R.color.backgorund))
        selectedTextDollar.setTextColor(ContextCompat.getColor(requireContext(), R.color.backgorund))
        if(binding.buttonPremium.background != onboardingButtonDrawable){
            binding.buttonPremium.background = onboardingButtonDrawable
        }

    }

    private fun resetTextColors() {
        binding.cardViewWeek.background = defaultBackgroundDrawable
        binding.cardViewMonth.background = defaultBackgroundDrawable
        binding.cardViewAnnual.background = defaultBackgroundDrawable

        binding.txtWeekly.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary1))
        binding.txtWeeklyDollar.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary1))
        binding.txtWeek.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary1))
        binding.txtWeekDollar.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary1))
        binding.txtAnnual.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary1))
        binding.txtAnnualDollar.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary1))
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }


    private fun setButtonEnabled(enabled: Boolean) {
        binding.buttonPremium.apply {
            isClickable = enabled
            isActivated = enabled
            isEnabled = enabled
        }
    }



}
