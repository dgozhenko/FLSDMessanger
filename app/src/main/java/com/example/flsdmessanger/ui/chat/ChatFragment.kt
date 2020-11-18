package com.example.flsdmessanger.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.flsdmessanger.databinding.ChatFragmentBinding

class ChatFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ChatFragmentBinding.inflate(inflater)
        val user = ChatFragmentArgs.fromBundle(requireArguments()).selectedUser
        (activity as AppCompatActivity).supportActionBar?.title = user.username
        return binding.root
    }
}