package com.example.pcstorage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object ChangeFragments {

    fun changeFragmentPassString( activity: FragmentActivity , fragment : Fragment , id : String , data : String){
        val bundle = Bundle()
        bundle.putString(id , data)
        fragment.arguments = bundle
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame , fragment)
            .addToBackStack(null)
            .commit()
    }
}