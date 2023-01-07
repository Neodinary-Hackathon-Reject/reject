package com.makeus.reject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makeus.reject.R
import com.makeus.reject.databinding.ItemSignupTagRvBinding

class SignupTagAdapter : ListAdapter<String, SignupTagAdapter.ViewHolder>(StringDiffCallback()) {

    inner class ViewHolder(private val binding: ItemSignupTagRvBinding) : RecyclerView.ViewHolder(binding.root) {
        private val mContext: Context = binding.root.context

        fun bind(item: String) {
            binding.tvTag.text = mContext.getString(R.string.signup_tag).format(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSignupTagRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StringDiffCallback() : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}