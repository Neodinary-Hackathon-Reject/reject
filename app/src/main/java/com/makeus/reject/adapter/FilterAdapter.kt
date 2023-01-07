package com.makeus.reject.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makeus.reject.R
import com.makeus.reject.adapter.model.Filter

class FilterAdapter : ListAdapter<Filter, RecyclerView.ViewHolder>(FilterComparator()) {
    private lateinit var listener: OnItemClickListener

    companion object ViewType {
        const val UNCHECKED = 0
        const val CHECKED = 1
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int): Int {
        val filter = getItem(position)

        return if (filter.type == "UNCHECKED") {
            UNCHECKED
        } else {
            CHECKED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            UNCHECKED -> UncheckedViewHolder.create(parent)
            else -> CheckedViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        when (getItemViewType(position)) {
            UNCHECKED -> (holder as UncheckedViewHolder).bind(current)
            else -> (holder as CheckedViewHolder).bind(current)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(it, position)
        }
    }

    fun getFilter(position: Int): Filter {
        return getItem(position)
    }

    class UncheckedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.textView)

        companion object {
            fun create(parent: ViewGroup): RecyclerView.ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.filter_item, parent, false)
                return UncheckedViewHolder(view)
            }
        }

        fun bind(filter: Filter) {
            textView.text = filter.name
        }
    }

    class CheckedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.textView)

        companion object {
            fun create(parent: ViewGroup): RecyclerView.ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.filter_item, parent, false)
                return CheckedViewHolder(view)
            }
        }

        fun bind(filter: Filter) {
            textView.text = filter.name
            textView.setTextColor(Color.parseColor("#FFFFFF"))
            textView.setBackgroundResource(R.drawable.bg_284ee3_radius_45)
        }
    }

    class FilterComparator : DiffUtil.ItemCallback<Filter>() {
        override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return oldItem == newItem
        }
    }
}