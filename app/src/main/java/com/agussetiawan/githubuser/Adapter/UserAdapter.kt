package com.agussetiawan.githubuser.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agussetiawan.githubuser.MainActivity
import com.agussetiawan.githubuser.Model.Users.Users
import com.agussetiawan.githubuser.databinding.ComponentCardUsersBinding
import com.bumptech.glide.Glide

class UserAdapter(
    private val callback: UserCallback
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val data = ArrayList<Users>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: ArrayList<Users>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

    interface UserCallback {
        fun onUserClick(user: Users)
    }


    inner class ViewHolder(private val binding: ComponentCardUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users) {
            with(binding) {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
//                    .placeholder(R.drawable.profile_picture)
                    .into(imgDetailPhoto)
                tvName.text = user.login
                tvType.text = user.type
                root.setOnClickListener { callback.onUserClick(user) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = ComponentCardUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}