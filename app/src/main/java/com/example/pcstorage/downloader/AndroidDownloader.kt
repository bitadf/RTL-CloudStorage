package com.example.pcstorage.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    val context: Context
) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    private val appName = "RTLCloudStorage"
    override fun startDownload(url: String, filePath: String, fileName: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setTitle(fileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS , "$appName/$filePath/$fileName")

        return downloadManager.enqueue(request)
    }

    override fun stopDownload(downloadId: Long) {
        downloadManager.remove(downloadId)
    }
}