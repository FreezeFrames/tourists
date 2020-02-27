package ru.freezeframeteam.tourists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mBottomNavigationView: BottomNavigationView
    lateinit var navHostFragment: NavHostFragment
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        mBottomNavigationView = findViewById(R.id.bottom_navigation_view)
        NavigationUI.setupWithNavController(mBottomNavigationView, navHostFragment.navController)
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

        }
        false
    }
    override fun onBackPressed() {

        if(index > 0){
            navHostFragment.navController.navigateUp()
            when(navHostFragment.navController.currentDestination!!.label){
//                getString(R.string.menu_main) -> {
//                    mBottomNavigationView.menu.getItem(0).isChecked = true
//                }
//                getString(R.string.menu_chat) -> {
//                    mBottomNavigationView.menu.getItem(1).isChecked = true
//                }
//                getString(R.string.menu_task) -> {
//                    mBottomNavigationView.menu.getItem(2).isChecked = true
//                }
//                getString(R.string.menu_notify) -> {
//                    mBottomNavigationView.menu.getItem(3).isChecked = true
//                }
//                getString(R.string.menu_company) -> {
//                    mBottomNavigationView.menu.getItem(4).isChecked = true
//                }
            }
        }else{
            super.onBackPressed()
        }
    }
}
