package com.example.pcstorage.downloader

interface Downloader {
    fun startDownload(url : String , filePath : String , fileName : String) : Long
    fun stopDownload(downloadId : Long)
}