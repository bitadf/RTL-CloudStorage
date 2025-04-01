package com.example.pcstorage.fragments.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pcstorage.MainActivity
import com.example.pcstorage.R
import com.example.pcstorage.databinding.FragmentLoginBinding
import com.example.pcstorage.databinding.FragmentShowFileBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //data
        val email = binding.loginEmail.text
        val pass = binding.loginPassword.text
        val sharedPref = requireActivity().getSharedPreferences("AppPrefs" , Context.MODE_PRIVATE)

        binding.loginBtn.setOnClickListener{
            if(email.isNotEmpty() && pass.isNotEmpty()){
                //if logged in
                sharedPref.edit().putBoolean("LoggedIn" , true).apply()
                sharedPref.edit().putString("Email",email.toString()).apply()
                sharedPref.edit().putString("AuthToken" , "").apply()
                ///and the token from api

                startActivity(Intent(requireContext() , MainActivity::class.java))
                requireActivity().finish()
            }
        }
        binding.loginSignupText.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.auth_frame_container , SignupFragment())
                .commit()
        }
    }

}