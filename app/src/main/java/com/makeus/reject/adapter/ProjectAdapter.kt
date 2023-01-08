package com.makeus.reject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makeus.reject.R
import com.makeus.reject.network.model.response.RoomDto

class ProjectAdapter(private val listener: OnItemClickListener) :
    ListAdapter<RoomDto, ProjectAdapter.ViewHolder>(ProjectComparator()) {

    interface OnItemClickListener {
        fun onItemClick(roomDto: RoomDto)
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
        private val titleText = itemView.findViewById<TextView>(R.id.titleText)
        private val introText = itemView.findViewById<TextView>(R.id.introText)
        private val keywordRecyclerView =
            itemView.findViewById<RecyclerView>(R.id.keywordRecyclerView)
        private val peopleText = itemView.findViewById<TextView>(R.id.peopleText)

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.project_item, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: RoomDto) {
            var msg = ""
            for (x in item.jobList) {
                msg += "${x} "
            }
            msg += "모집 중"

            val adapter = KeywordAdapter()
            keywordRecyclerView.adapter = adapter
            keywordRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val keywordList = item.tendencyList
            adapter.submitList(keywordList)

            titleText.text = msg
            peopleText.text = "${item.currentUserCount}/${item.maxUserCount}"
        }
    }

    class ProjectComparator : DiffUtil.ItemCallback<RoomDto>() {
        override fun areItemsTheSame(oldItem: RoomDto, newItem: RoomDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RoomDto, newItem: RoomDto): Boolean {
            return oldItem == newItem
        }
    }
}