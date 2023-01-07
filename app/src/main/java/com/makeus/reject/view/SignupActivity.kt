package com.makeus.reject.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.chip.Chip
import com.makeus.reject.App
import com.makeus.reject.R
import com.makeus.reject.adapter.SignupTagAdapter
import com.makeus.reject.base.BaseActivity
import com.makeus.reject.common.Consts
import com.makeus.reject.databinding.ActivitySignupBinding
import com.makeus.reject.utils.Utils
import com.makeus.reject.viewmodel.SignupViewModel
import com.makeus.reject.viewmodel.ViewModelFactory

class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    private val positionList = arrayListOf<String>("웹 개발자", "서버 개발자", "소프트웨어 엔지니어", "프론트엔드 개발자", "자바 개발자", "파이썬 개발자", "안드로이드 개발자", "ios 개발자", "스프링부트 개발자", "UX/UI 개발자", "프로덕트 매니저", "개발 매니저", "Node.js 개발자", "인공지능 개발자", "Python 개발자")
    private val meetingList = arrayListOf<String>("온라인", "오프라인", "온라인/오프라인 모두 가능")
    private val placeList = arrayListOf<String>("서울특별시", "경기", "부산광역시", "인천광역시", "대구광역시", "경상남도", "경상북도", "대전광역시", "충청남도", "전라남도", "전라북도", "광주광역시", "강원도", "울산광역시", "제주특별자치도", "세종특별자치도")
    private val interestKeywordList = arrayListOf<String>("Pr아이디어", "공공컨설팅", "서비스/콘텐츠기획", "머신러닝", "자바", "파이썬", "PM", "의료", "SNS 서비스", "임베디드 개발자", "PHP 개발자", "웹 퍼블리셔", "데이터 사이언티스트", "VR 엔지니어")


    private val signupViewModel: SignupViewModel by viewModels { ViewModelFactory() }

    private val tendencyAdapter = SignupTagAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initView()
    }

    private fun initData() {
        setObserver()
    }

    private fun initView() {
        binding.nsvSignupRoot.setPadding(0, App.statusHeight, 0, 0)
        initTendencyRvAdapter()

        binding.apply {
            etSignupEmail.doAfterTextChanged {
                signupViewModel.email.value = it.toString()
            }

            tvSignupEmailCheck.setOnClickListener {
                signupViewModel.checkEmailDuplication(etSignupEmail.text.toString())
            }

            etSignupPwd.doAfterTextChanged {
                signupViewModel.password.value = it.toString()
                signupViewModel.checkSamePasswordCheck()
            }

            etSignupPwdCheck.doAfterTextChanged {
                signupViewModel.checkPassword.value = it.toString()

                if (signupViewModel.checkSamePasswordCheck()) {
                    tvSignupDuplicationWarring.visibility = View.GONE
                } else {
                    tvSignupDuplicationWarring.visibility = View.VISIBLE
                }
            }

            etSignupNickCheck.doAfterTextChanged {
                signupViewModel.nickName.value = it.toString()
            }

            tvSignupPosition.setOnClickListener {
                Utils.showDropDownMenu(this@SignupActivity, it, positionList, PopupMenu.OnMenuItemClickListener { item ->
                    tvSignupPosition.text = item?.title
                    signupViewModel.position.value = item.itemId.toLong()
                    tvSignupPosition.setTextColor(getColor(R.color.black))
                    Log.e("rak", signupViewModel.position.toString())
                    true
                })
            }

            clSignupMeeting.setOnClickListener {
                Utils.showDropDownMenu(this@SignupActivity, it, meetingList, PopupMenu.OnMenuItemClickListener { item ->
                    tvSignupMeeting.text = item?.title
                    signupViewModel.meeting.value = item?.title.toString()
                    tvSignupMeeting.setTextColor(getColor(R.color.black))
                    true
                })
            }

            clSignupPlace.setOnClickListener {
                Utils.showDropDownMenu(this@SignupActivity, it, placeList, PopupMenu.OnMenuItemClickListener { item ->
                    tvSignupPlace.text = item?.title
                    signupViewModel.place.value = item?.title.toString()
                    tvSignupPlace.setTextColor(getColor(R.color.black))
                    true
                })
            }

            etSignupTendency.setOnEditorActionListener { textView, actioinId, keyEvent ->
                when (actioinId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        val tendencyList = signupViewModel.tendency.value
                        tendencyList!!.add(etSignupTendency.text.toString())
                        signupViewModel.tendency.value = tendencyList

                        true
                    }
                    else -> {
                        false
                    }
                }
            }

            etSignupIntroduce.doAfterTextChanged {
                signupViewModel.introduce.value = it.toString()
            }


            cgSignupInterestKeyword.setOnCheckedStateChangeListener { group, checkedIds ->
                val clickedChip = binding.cgSignupInterestKeyword.children.toList().filter { (it as Chip).isChecked }.joinToString(",") { (it as Chip).text }
                val clickedChipList: ArrayList<String> = clickedChip.split(",").toMutableList() as ArrayList<String>

                signupViewModel.interestKeywordList.value = clickedChipList.map { it.replace("#", "") }
            }

            tvSignupConfirm.setOnClickListener {
                signupViewModel.apply {
                    if (isCheckEmailDuplication.value!! &&
                        password.value!!.isNotEmpty() &&
                        checkSamePasswordCheck() &&
                        place.value!!.isNotEmpty() &&
                        position.value != -1L &&
                        interestKeywordList.value!!.isNotEmpty() &&
                        tendency.value!!.isNotEmpty()
                    ) {
                        Log.e("rak", "정규화 통과")
                        signupViewModel.postSignup()
                    } else {
                        Log.e("rak", place.value!!.isNotEmpty().toString())
                        Toast.makeText(this@SignupActivity, "필수항목을 채워주세요", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initTendencyRvAdapter() {
        binding.rvSignupTendency.adapter = tendencyAdapter
        binding.rvSignupTendency.animation = null
    }

    private fun setObserver() {
        signupViewModel.tendency.observe(this) {
            tendencyAdapter.submitList(it.toMutableList())
        }

        signupViewModel.isCheckEmailDuplication.observe(this) {
            if (it) {
                binding.tvSignupEmailCheck.apply {
                    text = "인증완료"
                }
                binding.tvSignupEmailCheck.isEnabled = false
            }
        }

        signupViewModel.jwt.observe(this) {
            if (it.isNotEmpty()) {
                App.sharedPreferences.edit().apply {
                    putString(Consts.X_ACCESS_TOKEN, it)
                    commit()
                }

                Intent(this@SignupActivity, MainActivity::class.java).run {
                    startActivity(this)
                }
            }
        }
    }
}