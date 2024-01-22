package com.okation.aivideocreator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentSecondPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondPageFragment : Fragment() {
    private lateinit var binding: FragmentSecondPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondPageBinding.inflate(inflater, container, false)
        binding.buttonNext2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_secondPageFragment_to_thirdPageFragment)
        }
        return binding.root
    }
}