package com.example.flsdmessanger.ui.new_message

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flsdmessanger.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class NewMessageViewModel: ViewModel() {

    private val _navigateToSelectedChat = MutableLiveData<User>()
    val navigateToSelectedChat: LiveData<User>
        get() = _navigateToSelectedChat

    private val _users = MutableLiveData<ArrayList<User>>()
     val users: LiveData<ArrayList<User>>
        get() = _users

    init {
        fetchUsers()
    }

   private fun fetchUsers() {
        val users = arrayListOf<User>()
        val reference = FirebaseDatabase.getInstance().getReference("/users")
        reference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        users.add(user)
                        _users.postValue(users)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    fun displayChat(user: User) {
        _navigateToSelectedChat.value = user
    }

    fun displayChatComplete() {
        _navigateToSelectedChat.value = null
    }
}