package com.example.uas0602.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(

    @SerializedName("idEvent")
    val idEvent: String? = null,

    @SerializedName("strLeague")
    val strLeague: String? = null,

    @SerializedName("strEvent")
    val strEvent: String? = null,

    @SerializedName("strHomeTeam")
    val strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    val strAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    val intHomeScore: String? = null,

    @SerializedName("intAwayScore")
    val intAwayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String? = null,

    @SerializedName("dateEvent")
    val dateEvent: String? = null,

    @SerializedName("strThumb")
    val strThumb: String? = null,

    @SerializedName("idHomeTeam")
    val idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    val idAwayTeam: String? = null,

    @SerializedName("strSport")
    val strSport: String? = null

): Parcelable


data class MatchResponse(

    @SerializedName("events")
    val matchResponse: List<Match>,

    @SerializedName("event")
    val searchMatchResponse: List<Match>

)