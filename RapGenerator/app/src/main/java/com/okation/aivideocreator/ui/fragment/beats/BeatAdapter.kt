package com.okation.aivideocreator.ui.fragment.beats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.entitiy.beat.BackingTrack
import com.okation.aivideocreator.databinding.BeatRowBinding

interface BeatClickedListener {
    fun onBeatClicked(beatId : String?, isPlaying : Boolean?)
}
class BeatAdapter(private val beatList: List<BackingTrack?>, private val beatClickedListener : BeatClickedListener) : RecyclerView.Adapter<BeatAdapter.ViewHolder>(){
    private val playingPositions = mutableListOf<Int>()

    class ViewHolder(val binding: BeatRowBinding) : RecyclerView.ViewHolder(binding.root) {
        var isPlaying: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BeatRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return beatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beat = beatList[position]
        holder.binding.textViewBeatName.text = beat?.name

        holder.binding.root.setOnClickListener {
            val isPlaying = holder.isPlaying
            val clickedPosition = holder.adapterPosition

            if (playingPositions.isNotEmpty()) {
                // Eğer önceden başka bir öğe çalınıyorsa, durdur
                val prevPlayingPosition = playingPositions[0]
                playingPositions.clear()

                notifyItemChanged(prevPlayingPosition)
                beatClickedListener.onBeatClicked(beatId = beatList[prevPlayingPosition]?.uuid, isPlaying = true)
            }

            if (isPlaying) {
                // Eğer tıklanan öğe çalınıyorsa, durdur
                playingPositions.remove(clickedPosition)
                holder.binding.imageView4.setImageResource(R.drawable.btn_play_beats)
                holder.binding.imageView5.visibility = View.GONE
                holder.binding.beatCard.setBackgroundResource(R.drawable.unselected_cardview)
                beatClickedListener.onBeatClicked(beatId = beat?.uuid, isPlaying = true)
            } else {
                // Tıklanan öğeyi çal
                playingPositions.add(clickedPosition)
                holder.binding.imageView4.setImageResource(R.drawable.ic_pause)
                holder.binding.imageView5.visibility = View.VISIBLE
                holder.binding.beatCard.setBackgroundResource(R.drawable.selected_card_beat)
                beatClickedListener.onBeatClicked(beatId = beat?.uuid, isPlaying = false)
            }
            holder.isPlaying = !isPlaying
        }
    }

}