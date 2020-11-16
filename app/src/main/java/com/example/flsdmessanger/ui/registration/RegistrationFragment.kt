package com.example.flsdmessanger.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flsdmessanger.databinding.RegistrationFragmentBinding
import kotlinx.android.synthetic.main.registration_fragment.*


class RegistrationFragment: Fragment() {

    private val registrationViewMode: RegistrationViewModel by lazy { ViewModelProvider(this).get(RegistrationViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RegistrationFragmentBinding.inflate(inflater)

        binding.authTypeChange.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }



        binding.registerButton.setOnClickListener {
            val email = email_edit_register.text.toString()
            val password = password_edit_register.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Missing email or password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registrationViewMode.registerUser(email, password)
        }

        return binding.root
    }
}