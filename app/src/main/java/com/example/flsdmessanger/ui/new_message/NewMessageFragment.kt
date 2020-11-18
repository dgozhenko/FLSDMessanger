package com.example.flsdmessanger.ui.new_message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flsdmessanger.databinding.NewMessageFragmentBinding
import kotlinx.android.synthetic.main.new_message_fragment.*

class NewMessageFragment: Fragment() {
    private val newMessageViewModel by lazy { ViewModelProvider(this).get(NewMessageViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = NewMessageFragmentBinding.inflate(inflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Select user"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = NewMessageAdapter()
        recycler_view_new_message.layoutManager = LinearLayoutManager(requireContext())
        recycler_view_new_message.adapter = adapter
        recycler_view_new_message.addItemDecoration(DividerItemDecoration(recycler_view_new_message.context, DividerItemDecoration.VERTICAL))
        newMessageViewModel.users.observe(viewLifecycleOwner, {
            adapter.setUser(it)
        })
    }
}