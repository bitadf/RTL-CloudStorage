package com.example.pcstorage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FolderViewModel : ViewModel() {
    private val _folderNames = MutableLiveData<List<String>>().apply {
        value = listOf("همه" , "تصاویر" , "فیلم ها" , "اسناد")
    }
    val folderName : LiveData<List<String>> = _folderNames

    fun addFolder(name : String){
        val currentList = _folderNames.value.orEmpty().toMutableList()
        currentList.add(name)
        _folderNames.value = currentList
    }

 }