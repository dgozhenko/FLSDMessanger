package com.example.flsdmessanger.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flsdmessanger.data.ChatMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class ChatViewModel: ViewModel() {

    private val _messageFrom = MutableLiveData<ChatMessage>()
    val messageFrom: LiveData<ChatMessage>
        get() = _messageFrom

    private val _messageTo = MutableLiveData<ChatMessage>()
    val messageTo: LiveData<ChatMessage>
        get() = _messageTo

    init {
        listenForMessages()
    }

     fun sendMessage(message: String, toId: String) {
         val reference = FirebaseDatabase.getInstance().getReference("/messages").push()
         val fromId = FirebaseAuth.getInstance().uid
         val chatMessage = ChatMessage(reference.key!!, message, fromId!!, toId, System.currentTimeMillis() / 1000)
         reference.setValue(chatMessage)
                 .addOnSuccessListener {
                     Log.d("ChatViewModel", "Send your message: ${reference.key}")
                 }
     }

   private fun listenForMessages() {
        val reference = FirebaseDatabase.getInstance().getReference("/messages")

        reference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java)
                snapshot.children.forEach {
                    if (chatMessage != null) {
                        if (chatMessage.fromId == FirebaseAuth.getInstance().uid ) {
                            Log.d("ChatViewModel", "From: ${chatMessage.text}")
                            _messageFrom.postValue(chatMessage)
                        } else {
                            "To: ${chatMessage.text}"
                            _messageTo.postValue(chatMessage)
                        }
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}