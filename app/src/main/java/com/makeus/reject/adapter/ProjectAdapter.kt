package com.makeus.reject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makeus.reject.R
import com.makeus.reject.adapter.model.Project

class ProjectAdapter(private val context: Context) :
    ListAdapter<Project, ProjectAdapter.ViewHolder>(ProjectComparator()) {
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, context)
        holder.itemView.setOnClickListener {
            listener.onItemClick(it, position)
        }
    }

    fun getProject(position: Int): Project {
        return getItem(position)
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

        fun bind(item: Project, context: Context) {
            val adapter = KeywordAdapter()
            keywordRecyclerView.adapter = adapter
            keywordRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val keywordList = listOf("#전략적인", "#성실한", "#열정적인")
            adapter.submitList(keywordList)

            titleText.text = item.title
            introText.text = item.intro
            peopleText.text = "${item.currentPeople}/${item.totalPeople}"
        }
    }

    class ProjectComparator : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem == newItem
        }
    }
}