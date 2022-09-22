package com.crystal.android.recyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.crystal.android.recyclerview.R
import com.crystal.android.recyclerview.datas.User

class UserAdapter(
    private val mContext: Context)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

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
        // 수정 전
        //val user = mList[position]

        // 수정 후
        val user = differ.currentList[position]
        holder.bind(user)
    }

    override fun getItemCount() =  differ.currentList.size

    // 추가
    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}