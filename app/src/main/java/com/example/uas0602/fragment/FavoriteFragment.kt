package com.example.uas0602.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uas0602.R
import com.example.uas0602.adapter.PagerFavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter = PagerFavoriteAdapter(childFragmentManager)
        viewPagerFavorite.adapter = fragmentAdapter
        tabLayoutFavorite.setupWithViewPager(viewPagerFavorite)
    }

}
