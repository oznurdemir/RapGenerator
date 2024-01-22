package com.okation.aivideocreator.ui.fragment.edit_lyrics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentEditLyricsBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class EditLyricsFragment : Fragment() {
    private lateinit var binding: FragmentEditLyricsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditLyricsBinding.inflate(inflater, container, false)
        val args : EditLyricsFragmentArgs by navArgs()

        binding.editTextRapSong.setText(args.rapSongEdit)
        val rapSongEdit = args.rapSongEdit

        binding.imageViewBackGeneratingLyrics.setOnClickListener {
            val action = EditLyricsFragmentDirections.actionEditLyricsFragmentToGeneratingFragment(rapSong = rapSongEdit)
            Navigation.findNavController(it).navigate(action)
        }

        binding.buttonSave.setOnClickListener {
            val rapSong = binding.editTextRapSong.text.toString()
            val action = EditLyricsFragmentDirections.actionEditLyricsFragmentToGeneratingFragment(rapSong = rapSong)
            Navigation.findNavController(it).navigate(action)
        }
        return binding.root
    }
}