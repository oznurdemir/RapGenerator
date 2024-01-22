package com.okation.aivideocreator.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.btnSettings.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homePageFragment_to_settingFragment)
        }
        binding.homeBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homePageFragment_to_promptFragment)
        }
        return binding.root
    }
}