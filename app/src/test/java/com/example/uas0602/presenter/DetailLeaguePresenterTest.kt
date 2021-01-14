package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.DetailLiga
import com.example.uas0602.model.LeagueResponse
import com.example.uas0602.view.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.*

class DetailLeaguePresenterTest {

    @Mock
    private lateinit var view: DetailLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailLeaguePresenter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        presenter = DetailLeaguePresenter(view, apiRepository, gson)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main Dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testGetDetails() {
        val league: MutableList<DetailLiga> = mutableListOf()
        val response = LeagueResponse(league)
        val leagueId = "4328"


        runBlocking {
            launch(Dispatchers.Main) {

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", LeagueResponse::class.java)).thenReturn(response)

            }
        }

            presenter.getDetails(leagueId)
            Thread.sleep(1000)
            Mockito.verify(view).showDetails(league)

    }

}