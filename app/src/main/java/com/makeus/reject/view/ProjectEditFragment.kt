package com.makeus.reject.view

import android.os.Bundle
import android.view.View
import com.makeus.reject.R
import com.makeus.reject.base.BaseFragment
import com.makeus.reject.databinding.FragmentProjectEditBinding

class ProjectEditFragment :
    BaseFragment<FragmentProjectEditBinding>(R.layout.fragment_project_edit) {

    private val positionList = arrayListOf<String>(
        "웹 개발자",
        "서버 개발자",
        "소프트웨어 엔지니어",
        "프론트엔드 개발자",
        "자바 개발자",
        "파이썬 개발자",
        "안드로이드 개발자",
        "ios 개발자",
        "스프링부트 개발자",
        "UX/UI 개발자",
        "프로덕트 매니저",
        "개발 매니저",
        "Node.js 개발자",
        "인공지능 개발자",
        "Python 개발자"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}