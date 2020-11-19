package com.example.flsdmessanger.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flsdmessanger.databinding.ChatFragmentBinding
import kotlinx.android.synthetic.main.chat_fragment.*

class ChatFragment: Fragment() {
    private val chatViewModel by lazy { ViewModelProvider(this).get(ChatViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = ChatFragmentBinding.inflate(inflater)
        val user = ChatFragmentArgs.fromBundle(requireArguments()).selectedUser
        (activity as AppCompatActivity).supportActionBar?.title = user.username
        val messageTextEdit = binding.sendEditText.text

        binding.sendButton.setOnClickListener {
            chatViewModel.sendMessage(messageTextEdit.toString(), user.uid)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ChatRecyclerFromAdapter()
        chat_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        chat_recycler_view.adapter = adapter

        chatViewModel.messageFrom.observe(viewLifecycleOwner, {
           adapter.setMessages(it)
        })

        chatViewModel.messageTo.observe(viewLifecycleOwner, {
            adapter.setMessages(it)
        })
    }
}