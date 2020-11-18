package com.example.flsdmessanger.ui.new_message

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.flsdmessanger.data.User
import com.example.flsdmessanger.databinding.NewMessageFragmentBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.new_message_fragment.*

class NewMessageFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = NewMessageFragmentBinding.inflate(inflater)
        (activity as AppCompatActivity).supportActionBar?.title = "Select user"

        val users = arrayListOf<User>()
        val reference = FirebaseDatabase.getInstance().getReference("/users")
        reference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        users.add(user)
                    }
                    val adapter = NewMessageAdapter(users)
                    recycler_view_new_message.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        return binding.root
    }
}