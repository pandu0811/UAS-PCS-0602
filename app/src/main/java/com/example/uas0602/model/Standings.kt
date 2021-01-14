package com.example.uas0602.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standings(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("teamId")
    val teamId: String? = null,

    @SerializedName("played")
    val played: String? = null,

    @SerializedName("goalsfor")
    val goalsFor: Int? = null,

    @SerializedName("win")
    val win: Int? = null,

    @SerializedName("draw")
    val draw: Int? = null,

    @SerializedName("loss")
    val loss: Int? = null,

    @SerializedName("total")
    val total: Int? = null
): Parcelable

data class StandingsResponse(

    @SerializedName("table")
    val standingsResponse: List<Standings>
)