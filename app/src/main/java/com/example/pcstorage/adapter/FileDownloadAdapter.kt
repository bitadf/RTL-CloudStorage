package com.example.pcstorage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.data.FileDownloadItem
import com.google.android.material.progressindicator.CircularProgressIndicator

class FileDownloadAdapter(
    private val context: Context ,
    private val downloadList : List<FileDownloadItem> ,
    private val onItemClick:((position : Int , item : FileDownloadItem) -> Unit)? = null): RecyclerView.Adapter<FileDownloadAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.file_name)
        val size = view.findViewById<TextView>(R.id.file_size)
        val downloadIcon = view.findViewById<ImageView>(R.id.file_cloud_download_icon)
        val deleteIcon = view.findViewById<ImageView>(R.id.file_delete_icon)
        val progressBar = view.findViewById<CircularProgressIndicator>(R.id.file_progress_indicator)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.download_file_card ,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return downloadList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = downloadList[position]
        holder.name.text = currentItem.name
        holder.size.text = currentItem.size

        holder.downloadIcon.setOnClickListener{
            holder.progressBar.isIndeterminate = true
            startStopDownloadUI(holder.progressBar)
        }
    }
    fun startStopDownloadUI(progressIndicator: CircularProgressIndicator){
        if(progressIndicator.visibility == View.GONE ){
            progressIndicator.visibility = View.VISIBLE
        }else{
            progressIndicator.visibility = View.GONE
        }
    }

}