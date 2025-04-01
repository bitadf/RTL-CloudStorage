package com.example.pcstorage.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pcstorage.R
import com.example.pcstorage.databinding.FragmentHomeBinding
import com.example.pcstorage.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private lateinit var binding : FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignupBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.signupName.text
        val email = binding.signupEmail.text
        val pass = binding.signupPassword.text


//        binding.signupBtn.setOnClickListener{
//            TODO()
//        }
        binding.signupLoginText.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.auth_frame_container , LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}