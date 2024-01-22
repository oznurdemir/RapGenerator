package com.okation.aivideocreator.ui.fragment.home_song_creator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentHomeSongCreatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeSongCreatorFragment : Fragment() {
    private lateinit var binding : FragmentHomeSongCreatorBinding
    private lateinit var songAdapter :HomeSongCreaterAdapter
    private val viewmodel : HomeSongCreatorViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeSongCreatorBinding.inflate(inflater, container, false)

        binding.btnSettings.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homeSongCreatorFragment)
        }

        binding.imageViewAddSong.setOnClickListener {
            viewmodel.songCount.observe(viewLifecycleOwner) { count ->
                if(count >= 3){
                    Navigation.findNavController(it).navigate(R.id.action_homeSongCreatorFragment_to_paymentFragment)
                }else{
                    Navigation.findNavController(it).navigate(R.id.action_homeSongCreatorFragment_to_promptFragment)
                }
            }
        }
        setUpRv()
        getAsgardPeopleList()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("PaymentFragment", "onDestroyView çağrıldı")
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
    private fun setUpRv() {
        songAdapter = HomeSongCreaterAdapter(requireContext(), viewmodel)
        binding.rvSong.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = songAdapter
            setHasFixedSize(true)
        }
    }
    private fun getAsgardPeopleList() {
        viewmodel.getSong().observe(viewLifecycleOwner){
            songAdapter.differ.submitList(it)
        }
    }
}