package com.example.pcstorage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.data.RecentUploadsItem

class HomeRecyclerAdapter(private val uploadList:List<RecentUploadsItem>):
    RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>(){

        class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
            val icon : ImageView = view.findViewById(R.id.home_icon_recycler)
            val title : TextView = view.findViewById(R.id.home_title_recycler)
            val date : TextView = view.findViewById(R.id.home_date_recycler)
            val size: TextView = view.findViewById(R.id.home_size_recycler)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_card , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uploadList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFile = uploadList[position]
        holder.title.text = currentFile.title
        holder.date.text = currentFile.date
        holder.size.text = currentFile.size

        ///TODO : change icon based on data type
    }
}