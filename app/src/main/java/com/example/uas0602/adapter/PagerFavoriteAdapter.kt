package com.example.uas0602.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.uas0602.fragment.FavoriteMatchFragment
import com.example.uas0602.fragment.FavoriteTeamFragment

class PagerFavoriteAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteMatchFragment()
            }

            else -> {
                FavoriteTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match"
            else -> {
                return "Teams"
            }
        }
    }

}