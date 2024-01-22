package com.okation.aivideocreator.ui.fragment.generatinglyrics

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.entitiy.ChatcptRequestBody
import com.okation.aivideocreator.databinding.FragmentGeneratingLyricsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratingLyricsFragment : Fragment() {
    private lateinit var binding: FragmentGeneratingLyricsBinding
    private lateinit var viewModel: GeneratingLyricsViewModel
    private lateinit var rapSong : String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGeneratingLyricsBinding.inflate(inflater, container, false)

        binding.imageViewBackPrompt.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_generatingLyricsFragment_to_promptFragment)
        }

        val args :GeneratingLyricsFragmentArgs by navArgs()
        val selectedContents = args.selectedContents

        binding.textViewContentRap.text = selectedContents

        val body = ChatcptRequestBody("gpt-3.5-turbo-instruct", selectedContents)
        viewModel.getRapSong(body)
        observeEvents()

        object :CountDownTimer(12000,1000){
            override fun onTick(p0: Long) {
                binding.textViewTime.text = "${p0/1000}s Left"
            }
            override fun onFinish() {}
        }.start()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: GeneratingLyricsViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun observeEvents() {
        viewModel.rapResponse.observe(viewLifecycleOwner) { rap ->
            rap.choices?.let { choices ->
                if (choices.isNotEmpty()) {
                    val firstChoice = choices[0]
                    val message = firstChoice?.text
                    if (!(message.isNullOrEmpty())) {
                        rapSong = message
                        val action = GeneratingLyricsFragmentDirections.actionGeneratingLyricsFragmentToGeneratingFragment(rapSong)
                        Navigation.findNavController(binding.textViewTime).navigate(action)
                    }
                }
            }
        }
    }
}