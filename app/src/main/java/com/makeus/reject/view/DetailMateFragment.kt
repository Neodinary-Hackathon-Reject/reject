package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.makeus.reject.R
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentDetailMateBinding
import com.makeus.reject.viewmodel.MateDetailViewModel
import com.makeus.reject.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class DetailMateFragment : BaseFragment<FragmentDetailMateBinding>(R.layout.fragment_detail_mate) {
    private var userId: Long = -1
    private val mateDetailViewModel: MateDetailViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            userId = getLong("userId")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mateDetailViewModel.getMateDetail(userId)
        mateDetailViewModel.mateDetail.observe(requireActivity()) { mateDetail ->
            if (!isAdded) return@observe
            lifecycleScope.launch {
                Glide
                    .with(requireContext())
                    .load(mateDetail.profileImgageUrl)
                    .circleCrop()
                    .into(binding.profileImgView)

                binding.nickname.text = mateDetail.nickname
                binding.place.text = mateDetail.region
                binding.tvOneWord.text = mateDetail.review
                binding.portfolio.text = mateDetail.portfolio

                for (i in 1 until mateDetail.feedBackList.size) {
                    val chip = Chip(requireContext())
                    chip.text = mateDetail.feedBackList[i]
                    chip.setChipBackgroundColorResource(R.color.color_f5f5f5)
                    binding.cgDetailMateFeedback.addView(chip)
                }

                for (i in 1 until mateDetail.keywordList.size) {
                    val chip = Chip(requireContext())
                    chip.text = mateDetail.keywordList[i]
                    chip.setChipBackgroundColorResource(R.color.color_f5f5f5)
                    binding.cgDetailMateKeyword.addView(chip)
                }
            }
        }
    }
}