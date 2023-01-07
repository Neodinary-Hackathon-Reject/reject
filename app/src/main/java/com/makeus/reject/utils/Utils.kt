package com.makeus.reject.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

class Utils {
    companion object {

        /**
         * 투명한 상단 statusbar가 포함된 풀스크린 모드를 액티비티에 적용하기 위한 메소드
         * 포포 프로젝트에서는 해당 메소드를 더 많이 사용할 것 같습니다.
         *
         * 안드로이드 킷캣(KITKAT, API 19) 이상에서 적용 가능한 로직이지만,
         * 해당 프로젝트의 minSDK는 28이기 때문에 상관없습니다.
         *
         * @param activity
         */
        @JvmStatic
        @SuppressLint("ObsoleteSdkInt")
        fun setFullScreenWithStatusBar(activity: AppCompatActivity) {
            activity.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    @Suppress("DEPRECATION")
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
            }
        }

        @JvmStatic
        fun showDropDownMenu(context: Context, view: View, menuList: List<String>, listener: PopupMenu.OnMenuItemClickListener) {
            PopupMenu(context, view).run {
                menuList.forEachIndexed { index, s ->
                    menu.add(0, index, 0, s)
                }
                setOnMenuItemClickListener(listener)
                show()
            }
        }
    }
}