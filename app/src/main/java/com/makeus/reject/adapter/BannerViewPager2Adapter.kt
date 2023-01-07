package com.makeus.reject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeus.reject.R
import com.makeus.reject.databinding.BannerItemBinding

class BannerViewPager2Adapter() : RecyclerView.Adapter<BannerViewPager2Adapter.ViewHolder>() {
    private var adImgList: ArrayList<Int>

    init {
        adImgList = initData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewPager2Adapter.ViewHolder {
        val binding =
            BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BannerViewPager2Adapter.ViewHolder,
        position: Int
    ) {
        holder.bind(adImgList[position % adImgList.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    inner class ViewHolder(var binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Int) {
            binding.imageView.setImageResource(data)
        }
    }


    fun initData(): ArrayList<Int> {
        return arrayListOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    }
}