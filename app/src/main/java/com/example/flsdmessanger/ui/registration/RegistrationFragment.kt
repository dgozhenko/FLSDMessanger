package com.example.flsdmessanger.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flsdmessanger.databinding.RegistrationFragmentBinding


class RegistrationFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RegistrationFragmentBinding.inflate(inflater)

        binding.authTypeChange.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }

        return binding.root
    }
}