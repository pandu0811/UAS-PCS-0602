package com.example.uas0602.presenter

import com.example.uas0602.api.ApiRepository
import com.example.uas0602.model.DetailMatch
import com.example.uas0602.model.DetailMatchRespose
import com.example.uas0602.view.DetailMatchView
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

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailMatchPresenter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        presenter = DetailMatchPresenter(view, apiRepository, gson)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main Dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getDetailMatch() {
        val league: MutableList<DetailMatch> = mutableListOf()
        val response = DetailMatchRespose(league)
        val idEvents = "441613"

        runBlocking {
            launch(Dispatchers.Main) {

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", DetailMatchRespose::class.java)).thenReturn(response)

            }
        }
        presenter.getDetailMatch(idEvents)
        Thread.sleep(1000)

        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDetailMatch(league)
    }
}