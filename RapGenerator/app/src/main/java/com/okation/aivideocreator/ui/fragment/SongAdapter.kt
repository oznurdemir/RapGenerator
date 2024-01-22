package com.okation.aivideocreator.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.data.Song
import com.okation.aivideocreator.databinding.SongRowBinding

class SongAdapter(val songList: List<Song>) :  RecyclerView.Adapter<SongAdapter.ViewHolder>(){

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
    var differ = AsyncListDiffer(this,diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SongRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            textViewSongName.text = currentItem.rapperName
            imageViewSong.setImageResource(currentItem.img)
            imageViewPlaySong.setOnClickListener {

            }
        }

    }
}

