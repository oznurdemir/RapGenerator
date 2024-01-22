package com.okation.aivideocreator.ui.fragment.beats

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentSelectedBeatBinding
import com.okation.aivideocreator.ui.shared_viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class SelectedBeatFragment : Fragment() {
    private lateinit var binding: FragmentSelectedBeatBinding
    private lateinit var viewModel: SelectedBeatViewModel
    private lateinit var beatAdapter: BeatAdapter
    private var mediaPlayer : MediaPlayer? = null
    private val sharedViewModelBeat : SharedViewModel by activityViewModels()
    private lateinit var saveBeatId : String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelectedBeatBinding.inflate(inflater, container, false)

        binding.imageViewBackEditLyrics.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_selectedBeatFragment_to_generatingFragment)
        }

        viewModel.beatUuid.observe(viewLifecycleOwner) { backingTracks ->
            backingTracks?.let {
                beatAdapter = BeatAdapter(backingTracks, object : BeatClickedListener {
                    override fun onBeatClicked(beatId: String?, isPlaying: Boolean?) {
                        beatId?.let {
                            saveBeatId = it
                            viewModel.showBeatUrl(beatId)
                            viewModel.beatUrl.observe(viewLifecycleOwner){beastUrls->
                                val url = beastUrls.backingTrack?.url
                                if(isPlaying == true){
                                    binding.buttonContinueBeat.setBackgroundResource(R.drawable.btn_unselected)
                                    pauseAudio()
                                    binding.buttonContinueBeat.isEnabled = false
                                }else{
                                    binding.buttonContinueBeat.setBackgroundResource(R.drawable.onboarding_button)
                                    playAudio(url.toString())
                                    binding.buttonContinueBeat.isEnabled = true
                                    binding.buttonContinueBeat.setOnClickListener {
                                        sharedViewModelBeat.savebeatUrl(saveBeatId)
                                        pauseAudio()
                                        Navigation.findNavController(it).navigate(R.id.action_selectedBeatFragment_to_rapperFragment)
                                    }
                                }
                            }
                        }
                    }
                })
                binding.rvSelectedBeat.adapter = beatAdapter
            }
        }
        return binding.root
    }

    fun playAudio(url: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } else {
            mediaPlayer!!.reset()
        }
        try {
            mediaPlayer!!.setDataSource(url)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun pauseAudio() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.pause()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SelectedBeatViewModel by viewModels()
        viewModel = tempViewModel
    }
}