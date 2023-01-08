package com.makeus.reject.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makeus.reject.App
import com.makeus.reject.R
import com.makeus.reject.base.BaseActivity
import com.makeus.reject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.layoutMainRoot.setPadding(0, App.statusHeight, 0, 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setIcon(R.drawable.ic_app_logo)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment? ?: return
        val navView: BottomNavigationView = binding.navView
        navController = host.navController
        navView.setupWithNavController(navController)
    }

    // 메뉴 리소스 XML의 내용을 앱바(App Bar)에 반영
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    fun hideAppbar() {
        binding.toolbar.visibility = View.GONE
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

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.fragment_home, R.id.fragment_project, R.id.fragment_mate, R.id.fragment_profile -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}