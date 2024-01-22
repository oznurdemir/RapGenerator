package com.okation.aivideocreator.ui.fragment.rapper

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.okation.aivideocreator.R
import com.okation.aivideocreator.databinding.FragmentRapperBinding
import com.okation.aivideocreator.ui.shared_viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class RapperFragment : Fragment() {
    private lateinit var binding: FragmentRapperBinding
    private lateinit var viewModel: RapperViewModel
    private lateinit var rapperAdapter: RapperAdapter
    private var mediaPlayerRapper: MediaPlayer? = null
    private var local_is_playing : Boolean = false
    private lateinit var rapper_id_SharedViewModel : String
    private var rapperNameSend : String = ""
    private val sharedViewModelRapper : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRapperBinding.inflate(inflater, container, false)
        binding.rvSelectedRapper.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.imageViewBackBeat.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_rapperFragment_to_selectedBeatFragment)
        }

        rapperAdapter = RapperAdapter(emptyList(), object : RapperClickedListener {
            override fun onRapperClicked(
                rapperId: String?,
                isPlaying: Boolean?,
                rapperName: String,
                rapperImageResId: Int
            ) {
                rapperNameSend = rapperName
                rapperId?.let {
                    rapper_id_SharedViewModel = it
                    Log.e("RapperID", rapperId)
                    viewModel.showRapperUrl(rapperId)
                    isPlaying?.let {
                        local_is_playing = isPlaying
                    }
                    sharedViewModelRapper.saveRapperImageResId(rapperImageResId)
                }
            }

            override fun onRapperClickedPause(isPlaying: Boolean?) {
                if(isPlaying == true){
                    binding.buttonContinueRapper.setBackgroundResource(R.drawable.btn_unselected)
                    pauseAudio()
                }
            }
        })
        viewModel.rapperUrl.observe(viewLifecycleOwner) { rapper ->
            rapper?.let {
                if(rapper.isNullOrEmpty()){
                    Snackbar.make(binding.imageViewBackBeat,"Beklenmeyen bir hata oluştu.", Snackbar.LENGTH_SHORT).show()
                }
                if (local_is_playing == false) {
                    binding.buttonContinueRapper.setBackgroundResource(R.drawable.onboarding_button)
                }
            }
        }

        binding.rvSelectedRapper.adapter = rapperAdapter

        viewModel.rapper.observe(viewLifecycleOwner) { rapper ->
            val firstTenRappers = rapper.take(10)
            rapperAdapter.updateData(firstTenRappers)
        }

        binding.buttonContinueRapper.setOnClickListener {
            sharedViewModelRapper.saverapperUrl(rapper_id_SharedViewModel)
            pauseAudio()
            val action = RapperFragmentDirections.actionRapperFragmentToGeneratingSongFragment(rapperNameSend)
            Navigation.findNavController(it).navigate(action)
        }

        return binding.root
    }

    fun playAudio(url: String) {
        if (mediaPlayerRapper == null) {
            mediaPlayerRapper = MediaPlayer()
            mediaPlayerRapper!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } else {
            mediaPlayerRapper!!.reset()
        }

        try {
            mediaPlayerRapper!!.setDataSource(url)
            mediaPlayerRapper!!.prepare()
            mediaPlayerRapper!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun pauseAudio() {
        if (mediaPlayerRapper != null && mediaPlayerRapper!!.isPlaying) {
            mediaPlayerRapper!!.pause()
            mediaPlayerRapper!!.reset()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: RapperViewModel by viewModels()
        viewModel = tempViewModel
    }
}
