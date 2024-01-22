package com.okation.aivideocreator.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentThirdPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdPageFragment : Fragment() {
    private lateinit var binding: FragmentThirdPageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentThirdPageBinding.inflate(inflater, container, false)

        binding.buttonNext3.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_thirdPageFragment_to_fourthFragment)
        }
        return binding.root
    }
}