package com.example.flsdmessanger.ui.new_message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flsdmessanger.R
import com.example.flsdmessanger.data.User
import de.hdodenhof.circleimageview.CircleImageView

class NewMessageAdapter(val user: ArrayList<User>): RecyclerView.Adapter<NewMessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.new_message_recycle_item, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(user[position])
    }

    override fun getItemCount(): Int {
        return user.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val usernameView = itemView.findViewById<TextView>(R.id.new_message_username)
        private val userImage = itemView.findViewById<CircleImageView>(R.id.new_message_profile_photo)

        fun bindItems(user: User) {
            usernameView.text = user.username
            Glide.with(userImage)
                .load(user.profileImage)
                .into(userImage)
        }
    }
}