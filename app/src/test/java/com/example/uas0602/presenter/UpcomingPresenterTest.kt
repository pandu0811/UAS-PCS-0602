package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.Match
import com.example.uas0602.model.MatchResponse
import com.example.uas0602.view.UpcomingMatchView
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

class UpcomingPresenterTest {

    @Mock
    private lateinit var view: UpcomingMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: UpcomingPresenter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        presenter = UpcomingPresenter(view, apiRepository, gson)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main Dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getUpcomingMatch() {
        val league: MutableList<Match> = mutableListOf()
        val response = MatchResponse(league, league)
        val idLeague = "4328"

        runBlocking {
            launch(Dispatchers.Main) {

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(response)

            }
        }

        presenter.getUpcomingMatch(idLeague)
        Thread.sleep(1000)
        Mockito.verify(view).showUpcomingMatch(league                       )
    }
}