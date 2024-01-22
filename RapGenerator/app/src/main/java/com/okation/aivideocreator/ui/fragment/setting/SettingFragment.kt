package com.okation.aivideocreator.ui.fragment.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var viewModel: SettingViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_settingFragment_to_homePageFragment)
        }

        binding.settingItemRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.settingItemList.observe(viewLifecycleOwner){
            val settingAdapter = SettingAdapter(it, requireContext())
            binding.settingItemRv.adapter = settingAdapter
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SettingViewModel by viewModels()
        viewModel = tempViewModel
    }
}