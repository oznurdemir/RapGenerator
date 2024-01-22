package com.okation.aivideocreator.ui.fragment.home_song_creator

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.databinding.SongRowBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class HomeSongCreaterAdapter(var mContext: Context, private val viewModel: HomeSongCreatorViewModel) :  RecyclerView.Adapter<HomeSongCreaterAdapter.ViewHolder>() {
    class ViewHolder(val binding: SongRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtilCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(
            oldItem: Song,
            newItem: Song
        ): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(
            oldItem: Song,
            newItem: Song
        ): Boolean {
            return oldItem == newItem
        }
    }
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var input: EditText
    var newSongName : String = ""

    var differ = AsyncListDiffer(this,diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSongCreaterAdapter.ViewHolder {
        return HomeSongCreaterAdapter.ViewHolder(
            SongRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    var isMusicPlaying = false
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.binding.apply {
            textViewSongName.text = currentItem.rapperName
            imageViewSong.setImageResource(currentItem.img)

            imageViewPlaySong.setOnClickListener {
                if (isMusicPlaying) {
                    holder.binding.imageViewPlaySong.setImageResource(R.drawable.ic_play_rapper)
                    mediaPlayer?.pause()
                } else {
                    holder.binding.imageViewPlaySong.setImageResource(R.drawable.ic_pause_rapper)
                    playMusic(currentItem.songUrl)
                }
                isMusicPlaying = !isMusicPlaying
            }
            btnMore.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(mContext)
                bottomSheetDialog.setContentView(R.layout.sample_dialog)

                val txtShare = bottomSheetDialog.findViewById<TextView>(R.id.sharedTxt)
                val txtRename = bottomSheetDialog.findViewById<TextView>(R.id.renameTxt)
                val txtDelete = bottomSheetDialog.findViewById<TextView>(R.id.deleteTxt)
                val txtCancel = bottomSheetDialog.findViewById<TextView>(R.id.cancelTxt)

                txtShare?.setOnClickListener {
                    bottomSheetDialog.dismiss()  // Bottom sheet'i kapat
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "audio/*" // MIME tipi olarak audio belirleyin
                        putExtra(Intent.EXTRA_STREAM, Uri.parse(currentItem.songUrl)) // currentItem'den URL alınmalı
                        putExtra(Intent.EXTRA_TITLE, "Introducing content previews")
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    }
                    val chooser = Intent.createChooser(shareIntent, null)
                    mContext.startActivity(chooser)
                }

                txtRename?.setOnClickListener {
                    bottomSheetDialog.dismiss()
                    val newSongName = showRenameDialog()
                    currentItem.rapperName = newSongName
                    notifyItemChanged(holder.adapterPosition)
                }

                txtDelete?.setOnClickListener {
                    bottomSheetDialog.dismiss()
                    val songToDelete = differ.currentList[position]
                    val alertDialog = AlertDialog.Builder(mContext).create()
                    alertDialog.setTitle("Delete The Fresh Prince of Awkward Situations?")
                    alertDialog.setMessage("Are you sure you want to delete this song?This action cannot be undone.")
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE") { dialogInterface, i ->
                        viewModel.deleteSong(songToDelete)
                        alertDialog.dismiss()
                    }
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL") { dialogInterface, i ->
                        alertDialog.dismiss()
                    }
                    alertDialog.show()
                }

                txtCancel?.setOnClickListener {
                    bottomSheetDialog.dismiss()
                }

                bottomSheetDialog.setCancelable(false)
                bottomSheetDialog.show()
            }
        }
    }

    private fun playMusic(audioUrl: String) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
            prepare()
            start()
            setOnCompletionListener {
                release()
                isMusicPlaying = false
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mediaPlayer?.release()
    }
    private fun showRenameDialog() : String {
        input = EditText(mContext)
        val alertDialog = AlertDialog.Builder(mContext).create()

        alertDialog.setTitle("Rename The Fresh Prince of Awkward Situations")
        alertDialog.setMessage("Please enter a new for this sone")
        alertDialog.setView(input)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialogInterface, i ->
            newSongName = input.text.toString()
            alertDialog.dismiss()
        }
        alertDialog.show()
        return newSongName
    }
}