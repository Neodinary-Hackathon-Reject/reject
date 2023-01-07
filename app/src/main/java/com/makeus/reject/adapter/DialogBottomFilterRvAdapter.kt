package com.makeus.reject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makeus.reject.databinding.ItemFilterBinding

class DialogBottomFilterRvAdapter(private val listener: OnClick) : ListAdapter<String, DialogBottomFilterRvAdapter.ViewHolder>(DiffCallback()) {
    private val positionList = arrayListOf<String>("웹 개발자", "서버 개발자", "소프트웨어 엔지니어", "프론트엔드 개발자", "자바 개발자", "파이썬 개발자", "안드로이드 개발자", "ios 개발자", "스프링부트 개발자", "UX/UI 개발자", "프로덕트 매니저", "개발 매니저", "Node.js 개발자", "인공지능 개발자", "Python 개발자")

    init {
        submitList(positionList)
    }

    interface OnClick {
        fun onClick(item: String)
    }

    inner class ViewHolder(private val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvFilter.text = item
            binding.tvFilter.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback() : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}