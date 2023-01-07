package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.BannerAdapter
import com.makeus.reject.adapter.CompetitionAdapter
import com.makeus.reject.adapter.MateAdapter
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var competitionAdapter: CompetitionAdapter
    private lateinit var mateAdapter: MateAdapter

    private val bannerList =
        listOf(R.drawable.bg_284ee3_radius_6dp, R.drawable.bg_ffffff_line_c6c6c6_radius_6dp)
    private val competitionList = listOf(
        R.drawable.competition_1,
        R.drawable.competition_2,
        R.drawable.competition_3,
        R.drawable.competition_4,
        R.drawable.competition_5
    )
    private val mateList =
        listOf(R.drawable.bg_284ee3_radius_6dp, R.drawable.bg_ffffff_line_c6c6c6_radius_6dp)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bannerAdapter = BannerAdapter(requireContext())

        binding.bannerRecyclerView.adapter = bannerAdapter
        binding.bannerRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        bannerAdapter.submitList(bannerList)

        competitionAdapter = CompetitionAdapter(requireContext())
        binding.competitionRecyclerView.adapter = competitionAdapter
        binding.competitionRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        competitionAdapter.submitList(competitionList)

        mateAdapter = MateAdapter(requireContext())
        binding.mateRecyclerView.adapter = mateAdapter
        binding.mateRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mateAdapter.submitList(mateList)
    }

}