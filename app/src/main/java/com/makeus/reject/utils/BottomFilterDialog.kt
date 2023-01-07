package com.makeus.reject.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.makeus.reject.R
import com.makeus.reject.adapter.DialogBottomFilterRvAdapter
import com.makeus.reject.databinding.DialogBottomFilterBinding


class BottomFilterDialog(private val listener: OpponentMenuCallbackListener) : DialogFragment() {
    private lateinit var binding: DialogBottomFilterBinding

    companion object {
        val TAG = "BottomFilterDialog"
    }

    interface OpponentMenuCallbackListener {
        fun onCallbackData(type: String)
    }

    private val adapter = DialogBottomFilterRvAdapter(object : DialogBottomFilterRvAdapter.OnClick {
        override fun onClick(item: String) {
            listener.onCallbackData(item)
            dismiss()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        //다이얼로그를 하단에 붙힘
        dialog?.window?.setGravity(Gravity.BOTTOM)

        /*
        다이얼로그는 테두리에 기본으로 마진이 되어있어서 바텀시트다이얼로그 처럼 화면에 딱 달라붙지 않는다.
        이를 해결하기 위해 임의로 다이얼로그의 마진을 없앴다. 또한 다이얼로그의 기존의 백그라운드를 투명으로 하여
        내가 지정한(위의 모서리가 둥근) 효과를 줄수 있도록 하였다.
         */
        try {
            var windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay
            val deviceSize = Point() //Point형 변수 생성
            display.getSize(deviceSize) //display에서 받아온 사이즈를 위에서 생성한 deviceSize에 저장

            val params = dialog?.window?.attributes
            params!!.width = deviceSize.x //다이얼로그의 가로를 디바이스 만큼 넓히기
            params!!.height = (deviceSize.y / 1.6).toInt() //다이얼로그의 세로를 디바이스의 세로의 60%만큼만 차지
            //params!!.horizontalMargin = 0.0f //가로의 마진 없애기인데 사실 이거 없어도 가로가 꽉참.

            //다이얼로그 팝업 애니메이션 효과 주기
            params.windowAnimations = R.style.AnimationPopupStyle

            val window = dialog?.window
            //다이얼로그를 띄우는 백그라운드를 투명하게하여 둥근 커스텀이 보이고 꽉차도록 만듬
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogBottomFilterBinding.inflate(layoutInflater) //viewBinding

        binding.rvBottomFilter.adapter = adapter
        return binding.root
    }
}