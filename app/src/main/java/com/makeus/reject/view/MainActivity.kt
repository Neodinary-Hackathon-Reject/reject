package com.makeus.reject.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makeus.reject.App
import com.makeus.reject.R
import com.makeus.reject.base.BaseActivity
import com.makeus.reject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.layoutMainRoot.setPadding(0, App.statusHeight, 0, 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setIcon(R.drawable.ic_app_logo)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment? ?: return
        val navView: BottomNavigationView = binding.navView
        val navController = host.navController
        navView.setupWithNavController(navController)

        this.onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    // 메뉴 리소스 XML의 내용을 앱바(App Bar)에 반영
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    //item 버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.search -> {
                // 검색
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}