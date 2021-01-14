package com.example.uas0602.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.uas0602.R
import com.example.uas0602.adapter.PagerAdapter
import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.DetailLiga
import com.example.uas0602.model.Liga
import com.example.uas0602.presenter.DetailLeaguePresenter
import com.example.uas0602.view.DetailLeagueView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_liga.*

class DetailLigaActivity : AppCompatActivity(), DetailLeagueView{

    private lateinit var presenter: DetailLeaguePresenter

    companion object {
        lateinit var idLeague: String
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_liga)

        val actionBar = supportActionBar

        val liga = intent.getParcelableExtra<Liga>("keyDetail")
        val request = ApiRepository()
        val gson = Gson()

        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.elevation = 0f

        presenter = DetailLeaguePresenter(this, request, gson)
        presenter.getDetails(liga?.id)

        idLeague = liga?.id!!

        liga.image?.let { Picasso.get().load(it).into(imgLeagueDetail) }
        tvNameLeagueDetail.text = liga.name


        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun showDetails(data: List<DetailLiga?>?) {
        tvDescLeagueDetail.text =data?.get(0)?.descLeague
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.item_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(applicationContext, SearchResultActivity::class.java)
                intent.putExtra(SearchResultActivity.ARGS_QUERY, query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
