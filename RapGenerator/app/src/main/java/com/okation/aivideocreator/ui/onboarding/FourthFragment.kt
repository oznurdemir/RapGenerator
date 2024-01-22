package com.okation.aivideocreator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentFourthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FourthFragment : Fragment() {
    private lateinit var binding: FragmentFourthBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        binding.buttonNext4.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fourthFragment_to_paymentFragment)
        }
        return binding.root
    }
}