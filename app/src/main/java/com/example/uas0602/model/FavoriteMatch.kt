package com.example.uas0602.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMatch(
    val idEvent: String? = null,

    val strEvent: String? = null,

    val intHomeScore: String? = null,

    val intAwayScore: String? = null,

    val dateEvent: String? = null,

    val idHomeTeam: String? = null,

    val idAwayTeam: String? = null
): Parcelable