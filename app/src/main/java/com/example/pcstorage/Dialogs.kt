package com.example.pcstorage

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getString
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object Dialogs {
    fun cancelUpload(context: Context , onConfirm:() -> Unit){

        val dialog = AlertDialog.Builder(context)
            .setMessage(ContextCompat.getString(context, R.string.cancel_upload))
            .setPositiveButton(ContextCompat.getString(context, R.string.ok)) { _, _ ->
                onConfirm.invoke()
            }
            .setNegativeButton(ContextCompat.getString(context, R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()

    }


    @SuppressLint("InflateParams")
    fun addNewFolder(context: Context, fragment: Fragment , viewModel : FolderViewModel) : String?{
        val dialogView = fragment.layoutInflater.inflate(R.layout.new_folder_dialog , null)
        val editText = dialogView.findViewById<EditText>(R.id.new_folder_dialog_edit_text)
        var folderName : String? = null
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setPositiveButton(R.string.ok){
                    dialog , which->
                folderName = editText.text.toString()
                if(!folderName.isNullOrEmpty()){
                    viewModel.addFolder(folderName!!)
                    Toast.makeText(context ,"پوشه ی جدید اضافه شد" , Toast.LENGTH_SHORT).show()
                }


            }
            .setNegativeButton(R.string.cancel){
                    dialog , which->
                dialog.dismiss()
            }
            .create()

        dialog.show()
        return folderName

    }
    fun selectFolder(context: Context ,
                     fragment: Fragment ,
                     viewModel: FolderViewModel ,
                     onFolderSelected : (String?) -> Unit) {
        val items = mutableListOf<String>()
        val deletedItems = listOf( "تصاویر" , "فیلم ها" , "اسناد")
        var selectedFolder : String? = null

        viewModel.folderName.observe(fragment.viewLifecycleOwner){
            folders->
            items.clear()
            folders.forEachIndexed { index, s ->
                if(!deletedItems.contains(s)){
                    items.add(s)
                }
            }
        }
        val selectAlert = AlertDialog.Builder(context)
            .setTitle(getString(context , R.string.select_folder))
            .setPositiveButton(getString(context , R.string.ok)){
                dialog , which ->
                onFolderSelected(selectedFolder)
                dialog.dismiss()

            }
            .setNegativeButton(getString(context , R.string.cancel)){
                dialog , which ->

                dialog.dismiss()
            }
            .setSingleChoiceItems(items.toTypedArray() ,0){
                dialog , which ->
                selectedFolder = items[which]

            }
            .create()
        selectAlert.show()

    }
}
