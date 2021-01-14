package com.example.uas0602.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMatchField(

    val id: Long? = null ,

    val idEvent: String? = null,

    val strEvent: String? = null,

    val intHomeScore: String? = null,

    val intAwayScore: String? = null,

    val dateEvent: String? = null,

    val idHomeTeam: String? = null,

    val idAwayTeam: String? = null

): Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val STR_EVENT: String = "STR_EVENT"
        const val SCORE_AWAY: String = "SCORE_AWAY"
        const val SCORE_HOME: String = "SCORE_HOME"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
    }
}

