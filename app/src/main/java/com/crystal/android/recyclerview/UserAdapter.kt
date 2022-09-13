package com.crystal.android.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val mContext: Context,
    private val mList: MutableList<User>
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val userImage: ImageView = itemView.findViewById(R.id.user_image)
        private val userNameText: TextView = itemView.findViewById(R.id.user_name_text)

        fun bind(user: User) {

            userNameText.text = user.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        holder.bind(user)
    }

    override fun getItemCount() = mList.size

}