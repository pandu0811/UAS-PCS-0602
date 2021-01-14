package com.example.uas0602.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeam(

    val idTeam: String? = null,

    val strTeam: String? = null,

    val strDescriptionEN: String? = null,

    val strCountry: String? = null,

    val strLeague: String? = null,

    val strStadium: String? = null,

    val strTeamBadge: String? = null
): Parcelable