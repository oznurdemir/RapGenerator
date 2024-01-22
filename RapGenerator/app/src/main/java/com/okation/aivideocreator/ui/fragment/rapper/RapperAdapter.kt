package com.okation.aivideocreator.ui.fragment.rapper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.entitiy.rap_rapper.RapRapperResponseItem
import com.okation.aivideocreator.databinding.RapperRowBinding

interface RapperClickedListener {
    fun onRapperClicked(rapperId : String?, isPlaying : Boolean?, rapperName : String, rapperImageResId: Int)
    fun onRapperClickedPause(isPlaying : Boolean?)
}
class RapperAdapter(
    private var rapperList: List<RapRapperResponseItem>,
    private val rapperClickedListener: RapperClickedListener
) : RecyclerView.Adapter<RapperAdapter.ViewHolder>(){

    class ViewHolder(val binding: RapperRowBinding) : RecyclerView.ViewHolder(binding.root){
        var isPlaying: Boolean = false
    }

    fun updateData(newList: List<RapRapperResponseItem>) {
        rapperList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return RapperAdapter.ViewHolder(RapperRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return rapperList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rapper = rapperList[position]

        holder.binding.textViewBeatName.text = rapper.displayName

        val rapperImageResId = when (position) {
            0 -> R.drawable.img_rapper1
            1 -> R.drawable.img_rapper2
            2 -> R.drawable.img_rapper3
            3 -> R.drawable.img_rapper4
            4 -> R.drawable.img_rapper5
            5 -> R.drawable.img_rapper6
            6 -> R.drawable.img_rapper7
            7 -> R.drawable.img_rapper8
            8 -> R.drawable.img_rapper10
            else -> R.drawable.img_rapper
        }

        holder.binding.imageViewRapper.setImageResource(rapperImageResId)

        holder.binding.imageViewRapper.setOnClickListener {
            val isPlaying = holder.isPlaying

            if (isPlaying) {
                holder.binding.imageViewPlaySong.setImageResource(R.drawable.ic_play_rapper)
                holder.binding.personImageLayout.setBackgroundResource(R.drawable.unselected_cardview)
                rapperClickedListener.onRapperClickedPause(isPlaying = true)
            } else {
                holder.binding.imageViewPlaySong.setImageResource(R.drawable.ic_pause_rapper)
                holder.binding.personImageLayout.setBackgroundResource(R.drawable.selected_cardview)
                rapperClickedListener.onRapperClicked(rapperId = rapper.voicemodelUuid, isPlaying = false, rapperName = holder.binding.textViewBeatName.text.toString(),rapperImageResId = rapperImageResId)
            }
            holder.isPlaying = !isPlaying
        }
    }
}