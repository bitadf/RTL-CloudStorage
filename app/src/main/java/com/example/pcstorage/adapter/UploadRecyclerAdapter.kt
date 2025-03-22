package com.example.pcstorage.adapter

import android.annotation.SuppressLint
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.data.AddUploadItem
import com.google.android.material.progressindicator.LinearProgressIndicator

class UploadRecyclerAdapter(private val uploadList :List<AddUploadItem>) :
RecyclerView.Adapter<UploadRecyclerAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val iconFolder : ImageView = view.findViewById(R.id.add_recycler_folder_icon)
        val iconClear : ImageView = view.findViewById(R.id.add_recycler_clear_icon)
        val title : TextView = view.findViewById(R.id.add_recycler_title)
        val date : TextView = view.findViewById(R.id.add_recycler_date)
        val cardView : CardView = view.findViewById(R.id.add_recycler_card)
        val percentageText : TextView = view.findViewById(R.id.add_recycler_progress_text)
        val progressBar : LinearProgressIndicator = view.findViewById(R.id.add_recycler_progressbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_recycler_card , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uploadList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFile = uploadList[position]
        holder.title.text = currentFile.title
        holder.title.setTextColor(currentFile.color)
        holder.date.text = currentFile.date
        holder.date.setTextColor(currentFile.color)
        holder.percentageText.text = "${currentFile.percentage.toString()}%"
        holder.percentageText.setTextColor(currentFile.color)
        holder.iconFolder.setColorFilter(currentFile.color)
        holder.iconClear.setColorFilter(currentFile.color)
        holder.cardView.setCardBackgroundColor(currentFile.bgColor)
        holder.progressBar.setProgress(currentFile.percentage)
        holder.progressBar.trackColor = currentFile.color
    }
}