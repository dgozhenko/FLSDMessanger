package com.example.flsdmessanger.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flsdmessanger.R
import com.example.flsdmessanger.data.ChatMessage
import com.google.firebase.auth.FirebaseAuth

private const val VIEW_TYPE_TO_MESSAGE = 1
private const val VIEW_TYPE_FROM_MESSAGE = 2

class ChatRecyclerFromAdapter: RecyclerView.Adapter<ChatRecyclerFromAdapter.ViewHolder>() {
    private var list: ArrayList<ChatMessage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == VIEW_TYPE_FROM_MESSAGE) {
            FromMessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_recycler_view_item_from, parent, false))
        } else {
            ToMessagesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_recycler_view_item_to, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val message = list[position]
        holder.bind(message)
    }

    override fun getItemViewType(position: Int): Int {
        val message = list[position]

        return if (message.fromId == FirebaseAuth.getInstance().uid) {
            VIEW_TYPE_TO_MESSAGE
        } else {
            VIEW_TYPE_FROM_MESSAGE
        }
    }

    fun getMessages(chatMessage: ArrayList<ChatMessage>){
        list = chatMessage
        notifyDataSetChanged()
    }

    fun setMessages(chatMessage: ChatMessage) {
        list.add(chatMessage)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

   inner class FromMessageViewHolder (itemView: View): ViewHolder(itemView) {
        private val messageText = itemView.findViewById<TextView>(R.id.message_text_view_chat)

       override fun bind(chatMessage: ChatMessage) {
           messageText.text = chatMessage.text
       }
    }

    inner class ToMessagesViewHolder(itemView: View): ViewHolder(itemView) {
        private val messageText = itemView.findViewById<TextView>(R.id.message_text_view_chat_to)
        override fun bind(chatMessage: ChatMessage) {
            messageText.text = chatMessage.text
        }
    }



   open class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       open fun bind (chatMessage: ChatMessage) {

        }
    }


}