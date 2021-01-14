package com.example.uas0602.model

import com.google.gson.annotations.SerializedName

data class DetailLiga(
    @SerializedName("dateFirstEvent")
    val dateLeague: String? = null,

    @SerializedName("strDescriptionEN")
    val descLeague: String? = null
)

data class LeagueResponse (
    @SerializedName("leagues")
    val league: List<DetailLiga?>? = null
)