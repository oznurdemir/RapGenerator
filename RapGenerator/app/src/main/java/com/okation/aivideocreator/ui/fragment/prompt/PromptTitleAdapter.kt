import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.Title
import com.okation.aivideocreator.databinding.TitleCardTasarimBinding

interface TitleClickedListener {
    fun onTitleClicked(titleId : Int?)
}

class PromptTitleAdapter(var titleList: List<Title>, private val titleClickedListener : TitleClickedListener) :
    RecyclerView.Adapter<PromptTitleAdapter.TitleCardHolder>() {

    private var selectedPosition: Int = 0

    inner class TitleCardHolder(var tasarim: TitleCardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    fun updateTitleList(newTitleList: List<Title>) {
        titleList = newTitleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleCardHolder {
        val binding = TitleCardTasarimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleCardHolder(binding)
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: TitleCardHolder, position: Int) {
        val title = titleList[position]
        val t = holder.tasarim

        t.textViewCategory.text = title.name

        val currentPosition = holder.adapterPosition
        if (selectedPosition == currentPosition) {
            t.cardViewCategory.setBackgroundResource(R.drawable.onboarding_button)
            t.textViewCategory.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        } else {
            t.cardViewCategory.setBackgroundResource(R.drawable.unselected_title_button)
            t.textViewCategory.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.secondary1
                )
            )
        }

        holder.itemView.setOnClickListener {
            selectedPosition = currentPosition
            notifyDataSetChanged()
            titleClickedListener.onTitleClicked(titleId = title?.id)
        }
    }
}
