package com.okation.aivideocreator.ui.fragment.prompt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.databinding.ContentCardTasarimBinding

interface ContentsClickedListener {
    fun onContentsClicked(contentText : String?)
}

class PromptContentsAdapter(var contentsList: List<String>, private val contentsClickedListener : ContentsClickedListener) : RecyclerView.Adapter<PromptContentsAdapter.ContentsCardHolder>(){
    inner class ContentsCardHolder(var tasarim: ContentCardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    fun updateContentsList(newContentsList: List<String>) {
        contentsList = newContentsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsCardHolder {
        val binding = ContentCardTasarimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentsCardHolder(binding)
    }

    override fun getItemCount(): Int {
        return contentsList.size
    }

    override fun onBindViewHolder(holder: ContentsCardHolder, position: Int) {
        val contents = contentsList[position]
        val t = holder.tasarim
        t.textView2.text = contents
        holder.itemView.setOnClickListener {
            contentsClickedListener.onContentsClicked(contentText = contents)
        }
    }
}