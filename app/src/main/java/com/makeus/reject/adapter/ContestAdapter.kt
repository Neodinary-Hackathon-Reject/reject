package com.makeus.reject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeus.reject.R
import com.makeus.reject.network.model.response.ContestDto

class ContestAdapter() :
    ListAdapter<ContestDto, ContestAdapter.ViewHolder>(CompetitionComparator()) {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.contest_item, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(image: ContestDto) {
            Glide.with(imageView)
                .load(image.imgUrl)
                .into(imageView)
        }
    }

    class CompetitionComparator : DiffUtil.ItemCallback<ContestDto>() {
        override fun areItemsTheSame(oldItem: ContestDto, newItem: ContestDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContestDto, newItem: ContestDto): Boolean {
            return oldItem == newItem
        }
    }
}