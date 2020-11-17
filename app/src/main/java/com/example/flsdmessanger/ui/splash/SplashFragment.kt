package com.example.flsdmessanger.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flsdmessanger.databinding.SplashScreenFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class SplashFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = SplashScreenFragmentBinding.inflate(inflater)

        if (FirebaseAuth.getInstance().uid == null) {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToRegistrationFragment())
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFeedFragment())
        }

        return binding.root
    }
}