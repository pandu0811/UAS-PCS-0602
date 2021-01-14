package com.example.uas0602.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeamField(

    val id: Long? = null,

    val idTeam: String? = null,

    val strTeam: String? = null,

    val strDescriptionEN: String? = null,

    val strCountry: String? = null,

    val strLeague: String? = null,

    val strStadium: String? = null,

    val strTeamBadge: String? = null
): Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_DESCRIPTION: String = "STR_DESCRIPTION"
        const val STR_COUNTRY: String = "STR_COUNTRY"
        const val STR_LEAGUE: String = "STR_LEAGUE"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_BADGE: String = "STR_BADGE"
    }
}