package com.example.uas0602.activity

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.example.uas0602.R
import com.example.uas0602.api.ApiRepository
import com.example.uas0602.database.database
import com.example.uas0602.model.DetailTeam
import com.example.uas0602.model.FavoriteTeam
import com.example.uas0602.model.FavoriteTeamField
import com.example.uas0602.presenter.DetailTeamPresenter
import com.example.uas0602.view.DetailTeamView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.*



class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var favorite: FavoriteTeam
    private lateinit var id: String
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    companion object {
        const val ARGS_ID = "ARGS_ID"
    }

    private lateinit var presenter: DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val actionBar = supportActionBar
        actionBar?.title = "Detail Team"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 0f

        val intent = intent
        val idTeam = intent.getStringExtra(ARGS_ID)
        val apiRepository =  ApiRepository()
        val gson = Gson()

        presenter = DetailTeamPresenter(this, apiRepository, gson)
        presenter.getTeamDetail(idTeam)


        id = idTeam!!

        favoriteState()
    }

    override fun showTeamDetail(data: List<DetailTeam>) {
        tvDescriptionTeam.text = data[0].strDescriptionEN
        tvStadiumTeamDetail.text = data[0].strStadium
        tvLeagueTeamDetail.text = data[0].strLeague
        tvCountryTeamDetail.text = data[0].strCountry
        tvNameTeamDetail.text = data[0].strTeam
        data.get(0).strTeamBadge.let { Picasso.get().load(it).into(imgLeagueDetail) }

        favorite = FavoriteTeam(data[0].idTeam,
            data[0].strTeam,
            data[0].strDescriptionEN,
            data[0].strCountry,
            data[0].strLeague,
            data[0].strStadium,
            data[0].strTeamBadge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_team_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite_team -> {
             if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            R.id.action_search_team -> {
                val searchView = item.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val intent = Intent(applicationContext, SearchTeamActivity::class.java)
                        intent.putExtra(SearchTeamActivity.ARGS_QUERY, query)
                        startActivity(intent)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeamField.TABLE_FAVORITE)
                .whereArgs("(ID_TEAM = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeamField>())
            if (favorite.isNotEmpty()) {
                isFavorite = true
            }
        }
    }


    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeamField.TABLE_FAVORITE,
                    FavoriteTeamField.ID_TEAM to favorite.idTeam,
                    FavoriteTeamField.STR_TEAM to favorite.strTeam,
                    FavoriteTeamField.STR_DESCRIPTION to favorite.strDescriptionEN,
                    FavoriteTeamField.STR_COUNTRY to favorite.strCountry,
                    FavoriteTeamField.STR_LEAGUE to favorite.strLeague,
                    FavoriteTeamField.STR_STADIUM to favorite.strStadium,
                    FavoriteTeamField.STR_BADGE to favorite.strTeamBadge)
            }
            Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, "Failed to Add Favorite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeamField.TABLE_FAVORITE, "(ID_TEAM = {id})",
                    "id" to id)
            }
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, "Failed to remove", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(1)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        } else {
            menuItem?.getItem(1)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
