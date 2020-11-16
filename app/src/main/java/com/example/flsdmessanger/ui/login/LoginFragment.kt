package com.example.flsdmessanger.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flsdmessanger.databinding.LoginFragmentBinding

class LoginFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = LoginFragmentBinding.inflate(inflater)
        binding.authTypeChange.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }

        return binding.root
    }
}