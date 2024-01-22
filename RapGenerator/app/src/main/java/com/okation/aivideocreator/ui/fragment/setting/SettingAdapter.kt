package com.okation.aivideocreator.ui.fragment.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.okation.aivideocreator.data.entitiy.setting.SettingItem
import com.okation.aivideocreator.databinding.ItemSettingCardBinding
import com.okation.aivideocreator.util.Constants

class SettingAdapter(var itemList: List<SettingItem>,val mContext : Context) : RecyclerView.Adapter<SettingAdapter.SettingCardHolder>(){

    inner class SettingCardHolder(var tasarim: ItemSettingCardBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingCardHolder {
        val binding = ItemSettingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingCardHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SettingCardHolder, position: Int) {
        val item = itemList[position]
        val t = holder.tasarim
        t.textViewItem.text = item.itemText

        holder.tasarim.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("${Constants.NEON_URL}")
            mContext.startActivity(intent)
        }
    }

}