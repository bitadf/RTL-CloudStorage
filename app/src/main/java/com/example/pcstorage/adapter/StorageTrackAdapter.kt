package com.example.pcstorage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.data.StorageTrackItem
import com.google.android.material.progressindicator.LinearProgressIndicator

class StorageTrackAdapter(private val storageItemList : List<StorageTrackItem>):
    RecyclerView.Adapter<StorageTrackAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val icon : ImageView = view.findViewById(R.id.storage_card_dot_icon)
        val title : TextView = view.findViewById(R.id.storage_card_title)
        val size : TextView = view.findViewById(R.id.storage_card_size)
        val progress : LinearProgressIndicator = view.findViewById(R.id.storage_card_progress_bar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.storage_card , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storageItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = storageItemList[position]
        holder.icon.setColorFilter(currentItem.color)
        holder.title.text = currentItem.title
        holder.size.text = "${currentItem.size.toString()} گیگ "
        holder.progress.trackColor = currentItem.color
        holder.progress.progress = currentItem.progress
    }
}