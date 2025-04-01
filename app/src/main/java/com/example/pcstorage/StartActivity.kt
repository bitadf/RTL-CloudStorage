package com.example.pcstorage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPref = getSharedPreferences("AppPrefs" , Context.MODE_PRIVATE)
        sharedPref.edit().putString("Email" , "").apply()
        sharedPref.edit().putString("AuthToken" , "").apply()
        sharedPref.edit().putString("Password" , "").apply()
        sharedPref.edit().putBoolean("LoggedIn" , false).apply()
        val isLoggedIn = sharedPref.getBoolean("LoggedIn" , false)




        if(isLoggedIn){
            startActivity(Intent(this , MainActivity::class.java))
        }
        else{
            startActivity(Intent(this , AuthActivity::class.java))
        }
        finish()
    }
}