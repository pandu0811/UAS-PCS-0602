package com.example.uas0602.activity

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.uas0602.R
import com.example.uas0602.api.ApiRepository
import com.example.uas0602.database.database
import com.example.uas0602.model.*
import com.example.uas0602.presenter.DetailMatchPresenter
import com.example.uas0602.presenter.TeamsPresenter
import com.example.uas0602.utils.invisible
import com.example.uas0602.utils.visible
import com.example.uas0602.view.DetailMatchView
import com.example.uas0602.view.TeamsView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_match.*
import org.jetbrains.anko.db.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView, TeamsView {

    lateinit var detailMatchPresenter: DetailMatchPresenter
    lateinit var teamsPresenter: TeamsPresenter
    private var menuItem: Menu? = null
    private lateinit var favorite: FavoriteMatch
    private lateinit var id: String
    private var isFavorite: Boolean = false

    companion object {
        const val ARGS_ID_AWAY = "ARGS_ID_AWAY"
        const val ARGS_ID_HOME = "ARGS_ID_HOME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_match)

        val actionBar = supportActionBar
        actionBar?.title = "Detail Match"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 0f

        val intent = intent
        val idHomeTeam = intent.getStringExtra(ARGS_ID_HOME)
        val idAwayTeam = intent.getStringExtra(ARGS_ID_AWAY)
        id = intent.getStringExtra("id")

        favoriteState()
        val apiRepository = ApiRepository()
        val gson = Gson()

        detailMatchPresenter = DetailMatchPresenter(this, apiRepository, gson)
        teamsPresenter = TeamsPresenter(this, apiRepository, gson)

        detailMatchPresenter.getDetailMatch(id)
        teamsPresenter.getTeams(idHomeTeam, idAwayTeam)
    }

    override fun showDetailMatch(data: List<DetailMatch?>?) {
        tvFormationHome.text = data?.get(0)?.strHomeGoalDetails
        tvFormationAway.text = data?.get(0)?.strAwayGoalDetails
        tvDetailEvent.text = data?.get(0)?.strEvent
        tvDetailDate.text = data?.get(0)?.dateEvent
        tvScoreDetailAway.text = data?.get(0)?.intAwayScore
        tvHomeGoalDetail.text = data?.get(0)?.intHomeScore

        favorite = FavoriteMatch(data?.get(0)?.idEvent,
            data?.get(0)?.strEvent,
            data?.get(0)?.intHomeScore,
            data?.get(0)?.intAwayScore,
            data?.get(0)?.dateEvent,
            data?.get(0)?.idHomeTeam,
            data?.get(0)?.idAwayTeam)
    }


    override fun showTeams(homeTeams: List<Teams>, awayTeams: List<Teams>) {
        homeTeams.get(0).strTeamBadge.let { Picasso.get().load(it).into(imgDetailHome) }
        awayTeams.get(0).strTeamBadge.let { Picasso.get().load(it).into(imgDetailAway) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_match_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else ->  {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatchField.TABLE_FAVORITE)
                .whereArgs("(ID_EVENT = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteMatchField>())
            if (favorite.isNotEmpty()) {
                isFavorite = true
            }
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteMatchField.TABLE_FAVORITE,
                    FavoriteMatchField.ID_EVENT to favorite.idEvent,
                    FavoriteMatchField.STR_EVENT to favorite.strEvent,
                    FavoriteMatchField.SCORE_AWAY to favorite.intAwayScore,
                    FavoriteMatchField.SCORE_HOME to favorite.intHomeScore,
                    FavoriteMatchField.DATE_EVENT to favorite.dateEvent,
                    FavoriteMatchField.ID_HOME_TEAM to favorite.idHomeTeam,
                    FavoriteMatchField.ID_AWAY_TEAM to favorite.idAwayTeam)
            }
            Toast.makeText(this, "Added To Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, "Fail Added To Favorite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatchField.TABLE_FAVORITE, "(ID_EVENT = {id})",
                    "id" to id)
            }
            Toast.makeText(this, "Remove To Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, "Fail Remove To From Favorite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun showLoading() {
        progressBarMatchDetail.visible()
    }

    override fun hideLoading() {
        progressBarMatchDetail.invisible()
    }


}
