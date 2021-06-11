package com.example.homeworkozge.data.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworkozge.R
import com.example.homeworkozge.data.model.User

class UserViewHolder (val container: ViewGroup,val listener: ((User) -> Unit)?) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(container.context).inflate
            (
            R.layout.list_item_user,
            container,
            false
        )
    ) {

    val avatar = itemView.findViewById<ImageView>(R.id.avatar)
    val username = itemView.findViewById<TextView>(R.id.txtUserName)
    val email = itemView.findViewById<TextView>(R.id.txtEmail)


    fun bind(userItem: User,context: Context) {

        username.text = userItem.name
        email.text = userItem.email
        Glide.with(context).load(userItem.avatar).into(avatar)

        itemView.setOnClickListener {
            listener?.invoke(userItem)
        }
    }

}