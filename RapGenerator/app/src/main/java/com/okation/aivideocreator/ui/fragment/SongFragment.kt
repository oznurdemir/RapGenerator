package com.okation.aivideocreator.ui.fragment
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.okation.aivideocreator.MainActivity
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.databinding.FragmentSongBinding
import com.okation.aivideocreator.ui.shared_viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongFragment : Fragment() {
    private lateinit var binding: FragmentSongBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var textViewTimeStart: TextView
    private lateinit var textViewTimeEnd: TextView
    private lateinit var buttonNext: Button
    private lateinit var imageViewPlay: ImageView
    private lateinit var imageViewBack: ImageView
    private lateinit var imageViewFoward: ImageView
    private var imgId : Int = 0
    private val sharedViewModelSong: SharedViewModel by activityViewModels()
    private lateinit var viewModel: SongViewModel

    private val handler = Handler(Looper.getMainLooper())
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SongViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        binding.imageViewBackRapper.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_songFragment_to_rapperFragment)
        }

        val args: SongFragmentArgs by navArgs()
        val audioUrl = args.urlSong
        val rapperName = args.rapperName

        sharedViewModelSong.rapperImageResId.observe(viewLifecycleOwner){
            imgId = it
            binding.imageViewRapperMedia.setImageResource(it)
        }

        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepare()

        seekBar = binding.seekBar
        textViewTimeStart = binding.textViewTimeStart
        textViewTimeEnd = binding.textViewTimeEnd
        buttonNext = binding.buttonNext
        imageViewPlay = binding.imageViewPlay
        imageViewBack = binding.imageViewBackMedia
        imageViewFoward = binding.imageViewFoward

        initializeSeekBar()

        imageViewPlay.setOnClickListener {
            if (isPlaying) {
                pauseMusic()
            } else {
                playMusic()
            }
            mediaPlayer.setOnCompletionListener {
                isPlaying = false
                imageViewPlay.setImageResource(R.drawable.btn_playsong)
            }
        }

        imageViewBack.setOnClickListener {
            rewindMusic()
        }

        imageViewFoward.setOnClickListener {
            forwardMusic()
        }

        binding.buttonNext.setOnClickListener {
            val song = Song(0,rapperName,imgId,audioUrl)
            viewModel.addSong(song)
            Navigation.findNavController(it).navigate(R.id.action_songFragment_to_homeSongCreatorFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("PaymentFragment", "onDestroyView çağrıldı")
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }


    private fun initializeSeekBar() {
        seekBar.max = mediaPlayer.duration

        mediaPlayer.setOnPreparedListener {
            val duration = mediaPlayer.duration
            val minutes = duration / 1000 / 60
            val seconds = duration / 1000 % 60
            textViewTimeEnd.text = String.format("%02d:%02d", minutes, seconds)
            updateSeekBar()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition

        val minutes = mediaPlayer.currentPosition / 1000 / 60
        val seconds = mediaPlayer.currentPosition / 1000 % 60
        textViewTimeStart.text = String.format("%02d:%02d", minutes, seconds)

        if (isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    private fun playMusic() {
        mediaPlayer.start()
        isPlaying = true
        imageViewPlay.setImageResource(R.drawable.btn_pausesong)
        updateSeekBar()
    }

    private fun pauseMusic() {
        mediaPlayer.pause()
        isPlaying = false
        imageViewPlay.setImageResource(R.drawable.btn_playsong)
    }

    private fun rewindMusic() {
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = if (currentPosition - 15000 > 0) currentPosition - 15000 else 0
        mediaPlayer.seekTo(newPosition)
    }

    private fun forwardMusic() {
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = if (currentPosition + 15000 < mediaPlayer.duration) currentPosition + 15000 else mediaPlayer.duration
        mediaPlayer.seekTo(newPosition)
    }
}
