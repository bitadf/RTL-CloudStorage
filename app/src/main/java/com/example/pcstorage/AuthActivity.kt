package com.example.pcstorage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.pcstorage.databinding.ActivityAuthBinding
import com.example.pcstorage.fragments.auth.LoginFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null){
            changeFragment(LoginFragment())
        }

    }
    fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_frame_container , fragment)
            .commit()
    }
}