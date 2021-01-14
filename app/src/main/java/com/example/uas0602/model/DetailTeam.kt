package com.example.uas0602.model

import com.google.gson.annotations.SerializedName

data class DetailTeam(

    @SerializedName("strTeamBadge")
    val strTeamBadge: String? = null,

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
    val strLeague: String? = null,

    @SerializedName("idTeam")
    val idTeam: String? = null
)


data class DetailTeamResponse(

    @SerializedName("teams")
    val detailTeamsResponse: List<DetailTeam>

)