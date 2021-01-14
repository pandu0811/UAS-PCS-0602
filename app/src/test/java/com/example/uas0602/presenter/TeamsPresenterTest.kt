package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.Teams
import com.example.uas0602.model.TeamsResponse
import com.example.uas0602.view.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {

    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TeamsPresenter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        presenter = TeamsPresenter(view, apiRepository, gson)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main Dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getTeams() {
        val teams: MutableList<Teams> = mutableListOf()
        val response = TeamsResponse(teams)
        val idHomeTeam = "133602"
        val idAwayTeam = "133614"

        runBlocking {
            launch(Dispatchers.Main) {

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", TeamsResponse::class.java)).thenReturn(response)

            }
        }

        presenter.getTeams(idHomeTeam, idAwayTeam)
        Thread.sleep(1000)
        Mockito.verify(view).showTeams(teams, teams)

    }
}