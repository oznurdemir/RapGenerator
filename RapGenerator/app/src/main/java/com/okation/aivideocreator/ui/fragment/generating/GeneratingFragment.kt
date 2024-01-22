package com.okation.aivideocreator.ui.fragment.generating

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentGeneratingBinding
import com.okation.aivideocreator.ui.shared_viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratingFragment : Fragment() {
    val args : GeneratingFragmentArgs by navArgs()
    private lateinit var binding: FragmentGeneratingBinding
    private val sharedViewModelGenerating : SharedViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGeneratingBinding.inflate(inflater, container, false)
        binding.textViewRapSong.movementMethod = ScrollingMovementMethod()
        binding.textViewRapSong.text = args.rapSong
        val editRap = binding.textViewRapSong.text.trim()

        binding.imageViewEdit.setOnClickListener {
            val action = GeneratingFragmentDirections.actionGeneratingFragmentToEditLyricsFragment(rapSongEdit = editRap.toString())
            Navigation.findNavController(it).navigate(action)
        }

        binding.buttonGoBeat.setOnClickListener {
            sharedViewModelGenerating.saveRap(binding.textViewRapSong.text.toString())
            Navigation.findNavController(it).navigate(R.id.action_generatingFragment_to_selectedBeatFragment)
        }
        return binding.root
    }
}