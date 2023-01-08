package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.makeus.reject.R
import com.makeus.reject.adapter.FilterAdapter
import com.makeus.reject.adapter.MateAdapterSecond
import com.makeus.reject.adapter.model.Filter
import com.makeus.reject.adapter.model.User
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentMateBinding
import com.makeus.reject.viewmodel.MateViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class MateFragment : BaseFragment<FragmentMateBinding>(R.layout.fragment_mate) {
    private lateinit var mateAdapter: MateAdapterSecond
    private lateinit var filterAdapter: FilterAdapter

    private val mateViewModel: MateViewModel by viewModels { ViewModelFactory() }

    private val filterList =
        listOf(Filter("UNCHECKED", "지역"), Filter("CHECKED", "직무"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mateAdapter = MateAdapterSecond(object : MateAdapterSecond.OnItemClickListener {
            override fun onItemClick(user: User) {
                val bundle = bundleOf("userId" to user.userId)

                findNavController().navigate(
                    R.id.action_fragment_mate_to_detailMateFragment2,
                    bundle
                )
            }
        })

        binding.mateRecyclerView.adapter = mateAdapter
        binding.mateRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)

        mateViewModel.mateList.observe(viewLifecycleOwner) {
            if (!isAdded) return@observe
            mateAdapter.submitList(it)
        }

        filterAdapter = FilterAdapter()
        binding.filterRecyclerView.adapter = filterAdapter
        binding.filterRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filterAdapter.submitList(filterList)
    }

}