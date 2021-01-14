package com.example.uas0602.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uas0602.R
import com.example.uas0602.fragment.FavoriteFragment
import com.example.uas0602.fragment.LeagueFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation.setOnNavigationItemSelectedListener {
            item -> when (item.itemId) {
                R.id.league -> {
                    loadLeagueFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.league
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadLeagueFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LeagueFragment(), LeagueFragment::class.java.simpleName)
                .commit()
        }
    }



}
