package com.okation.aivideocreator.ui.fragment.generating_song

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.data.entitiy.music.MusicRequestBody
import com.okation.aivideocreator.databinding.FragmentGeneratingSongBinding
import com.okation.aivideocreator.ui.shared_viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratingSongFragment : Fragment() {
    private lateinit var binding: FragmentGeneratingSongBinding
    private lateinit var viewModel: GeneratingSongViewModel
    private var beatID: String = ""
    private var rapID: String = ""
    private var rapperName: String = ""
    private var satirlar: List<List<String>> = emptyList()
    private lateinit var urlSong: String
    private val sharedViewModelGeneratingSong: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGeneratingSongBinding.inflate(inflater, container, false)
        val args: GeneratingSongFragmentArgs by navArgs()
        rapperName = args.rapperName

        sharedViewModelGeneratingSong.rapperImageResId.observe(viewLifecycleOwner){
            binding.rapperImage.apply {
                val sizeInDp = 123
                val layoutParams = layoutParams
                layoutParams.width = sizeInDp.toPx(requireContext())
                layoutParams.height = sizeInDp.toPx(requireContext())
                setImageResource(it)
            }
        }
        binding.rapperTxt.text = rapperName

        sharedViewModelGeneratingSong.rap.observe(viewLifecycleOwner) { rap ->
            val newRap = rap.trim()
            val paragraflar = newRap.split("\n\n")
            satirlar = paragraflar.map { it.split("\n") }

            sharedViewModelGeneratingSong.beatUrl.observe(viewLifecycleOwner) { beatUrl ->
                beatID = beatUrl

                sharedViewModelGeneratingSong.rapperUrl.observe(viewLifecycleOwner) { rapperUrl ->
                    rapID = rapperUrl
                    val body = MusicRequestBody(satirlar, beatID, rapID)
                    viewModel.getRapSong(body)
                    observeEvents()
                }
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: GeneratingSongViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun observeEvents() {
        viewModel.musicResponse.observe(viewLifecycleOwner) { music ->
            music.mixUrl?.let {
                urlSong = music.mixUrl

                val action =
                    GeneratingSongFragmentDirections.actionGeneratingSongFragmentToSongFragment(
                        rapperName = rapperName,
                        urlSong = urlSong
                    )
                Navigation.findNavController(binding.rapperTxt).navigate(action)
            }
            }
        }
    }

fun Int.toPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
    ).toInt()
}

