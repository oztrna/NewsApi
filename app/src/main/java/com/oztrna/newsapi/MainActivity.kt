package com.oztrna.newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.oztrna.newsapi.fragment.FavoriteFragment
import com.oztrna.newsapi.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var homeFragment = HomeFragment()
    private var favoriteFragment = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(homeFragment)

        bottom_nav_bar?.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    setFragment(homeFragment)
                    true
                }
                R.id.nav_favorites -> {
                    setFragment(favoriteFragment)
                    true
                }
                else -> {
                    setFragment(homeFragment)
                    true
                }
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, fragment)
        fragmentTransaction.commit()
    }
}