package com.example.pcstorage.downloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE"){
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID , -1L)
            if(id != -1L){
                Toast.makeText(context , "Download finished!" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}