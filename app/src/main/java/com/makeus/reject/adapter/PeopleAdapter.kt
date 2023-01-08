package com.makeus.reject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeus.reject.R
import com.makeus.reject.network.model.response.UserDto

class PeopleAdapter(private val context: Context) :
    ListAdapter<UserDto, PeopleAdapter.ViewHolder>(PeopleComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, context)
    }

    fun getPeople(position: Int): UserDto {
        return getItem(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImgView = itemView.findViewById<ImageView>(R.id.profileImgView)
        private val nickname = itemView.findViewById<TextView>(R.id.nickname)
        private val job = itemView.findViewById<TextView>(R.id.job)
        private val keywordRecyclerView =
            itemView.findViewById<RecyclerView>(R.id.keywordRecyclerView)

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.people_item, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: UserDto, context: Context) {
            val adapter = KeywordAdapter()
            keywordRecyclerView.adapter = adapter
            keywordRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter.submitList(item.tendencyList)

            Glide
                .with(context)
                .load(item.profileImageUrl)
                .circleCrop()
                .into(profileImgView)
            nickname.text = item.nickName
            job.text = item.job
        }
    }

    class PeopleComparator : DiffUtil.ItemCallback<UserDto>() {
        override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
            return oldItem == newItem
        }
    }
}