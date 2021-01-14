package com.example.uas0602.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(

    @SerializedName("strTeamBadge")
    val strTeamBadge: String? = null,

    @SerializedName("idTeam")
    val idTeam: String? = null,

    @SerializedName("strTeam")
    val strTeam: String? = null,

    @SerializedName("intFormedYear")
    val intFormedYear: String? = null,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String? = null,

    @SerializedName("strCountry")
    val strCountry: String? = null,

    @SerializedName("strStadium")
    val strStadium: String? = null,

    @SerializedName("strLeague")
    val strLeague: String? = null
): Parcelable

data class TeamsResponse(

    @SerializedName("teams")
    val teamsResponse: List<Teams>

)

data class SearchTeamResponse(

    @SerializedName("teams")
    val searchTeamResponse: List<Teams>
)