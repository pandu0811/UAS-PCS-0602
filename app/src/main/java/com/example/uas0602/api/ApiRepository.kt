package com.example.uas0602.api

import com.example.uas0602.BuildConfig
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }

    object TheSportDBApi {

        fun getDetailLeague(idLeague: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupleague.php?id=" + idLeague
        }

        fun getPreviousMatch(idLeague: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idLeague
        }

        fun getUpcomingMatch(idLeague: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idLeague
        }

        fun getTeamDetails(idTeams: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + idTeams
        }

        fun getMatchDetails(idEvents: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + idEvents
        }

        fun getSearchMatch(matchName: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + matchName
        }

        fun getStandings(idLeague: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookuptable.php?l=" + idLeague
        }

        fun getTeamList(idLeague: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_teams.php?id=" + idLeague
        }

        fun getSearchTeam(teamName: String?): String {
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + teamName
        }
    }
}

