package com.makeus.reject.view

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.makeus.reject.R
import com.makeus.reject.adapter.BannerViewPager2Adapter
import com.makeus.reject.adapter.ContestAdapter
import com.makeus.reject.adapter.MateAdapterSecond
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentHomeBinding
import com.makeus.reject.viewmodel.HomeViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private var bannerAdapter: BannerViewPager2Adapter = BannerViewPager2Adapter()
    private lateinit var competitionAdapter: ContestAdapter
    private lateinit var mateAdapter: MateAdapterSecond
    private val intervalTime = 3000.toLong() // 몇초 간격으로 페이지를 넘길것인지 (1500 = 1.5초)
    private var currentPosition = Int.MAX_VALUE / 2
    private var myHandler = MyHandler() // 배너를 자동으로 스크롤링 하기 위한 핸들러

    private val homeViewModel: HomeViewModel by viewModels { ViewModelFactory() }

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

        setObserve()

        //Ad viewPager 어댑터 장착
        binding.bannerVp.adapter = bannerAdapter
        binding.bannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL //스크롤 방향을 가로로 지정

        //Ad viewPager 현재배너 출력하는 코드
        if (bannerAdapter.initData().size > 3) //1의자리 숫자면 앞에 0을 붙여야 하기 때문에 if문으로 확인해 준다.
            binding.textViewTotalBanner.text = bannerAdapter.initData().size.toString() //리스트 최대 길이 만큼 total banner 텍스트를 변경해준다.
        else
            binding.textViewTotalBanner.text = "0${bannerAdapter.initData().size}" //리스트 최대 길이 만큼 total banner 텍스트를 변경해준다.

        binding.bannerVp.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPosition = position
                    if (position % bannerAdapter.initData().size > 3)
                        binding.textViewCurrentBanner.text = "${(position % bannerAdapter.initData().size) + 1}"
                    else
                        binding.textViewCurrentBanner.text = "0${(position % bannerAdapter.initData().size) + 1}"
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }



        competitionAdapter = ContestAdapter()
        binding.competitionRecyclerView.adapter = competitionAdapter
        binding.competitionRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        mateAdapter = MateAdapterSecond(requireContext())
        binding.mateRecyclerView.adapter = mateAdapter

    }

    private fun setObserve() {
        homeViewModel.recommendProjectList.observe(viewLifecycleOwner) {
            competitionAdapter.submitList(it)
        }

        homeViewModel.recommendUserList.observe(viewLifecycleOwner) {
            mateAdapter.submitList(it)
        }
    }

    /*
    광고 배너 자동으로 움직이도록 구현하는 코드
     */
    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    /*
    핸들러를 중지시키는 코드
     */
    private fun autoScrollStop() {
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }


    private inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0) {
                binding.bannerVp.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    // 다른 페이지 갔다가 돌아오면 다시 스크롤 시작
    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    // 다른 페이지로 떠나있는 동안 스크롤이 동작할 필요는 없음. 정지
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

}
