package com.makeus.reject.adapter

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
import com.makeus.reject.adapter.model.User

class MateAdapterSecond(private val listener: OnItemClickListener) :
    ListAdapter<User, MateAdapterSecond.ViewHolder>(MateComparator()) {

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            listener.onItemClick(getItem(position))
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImgView = itemView.findViewById<ImageView>(R.id.profileImgView)
        private val nickname = itemView.findViewById<TextView>(R.id.nickname)
        private val region = itemView.findViewById<TextView>(R.id.email)
        private val job = itemView.findViewById<TextView>(R.id.job)
        private val keywordRecyclerView =
            itemView.findViewById<RecyclerView>(R.id.keywordRecyclerView)

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.mate_item_second, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: User) {
            val adapter = KeywordAdapter()
            keywordRecyclerView.adapter = adapter
            keywordRecyclerView.layoutManager =
                LinearLayoutManager(profileImgView.context, LinearLayoutManager.HORIZONTAL, false)
            val keywordList = item.tendencyList
            adapter.submitList(keywordList)

            Glide
                .with(profileImgView)
                .load(item.profileImg)
                .circleCrop()
                .into(profileImgView)
            nickname.text = item.nickName
            region.text = item.address
            job.text = item.job
        }
    }

    class MateComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}