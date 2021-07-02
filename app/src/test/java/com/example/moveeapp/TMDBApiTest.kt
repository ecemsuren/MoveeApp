package com.example.moveeapp

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moveeapp.ui.modules.main.view.MainActivityView
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TmdbApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var errorResponse: MockResponse
    private lateinit var errorInvalidResponse: MockResponse

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivityView::class.java, false, false)

    @Before
    fun setUp() {
        val errorJson = "error_body.json"
        val errorInvalidJson = "error_body_invalid.json"

        mockWebServer = MockWebServer()
        mockWebServer.start()
        mockWebServer.url("/")

        errorResponse = MockResponse()
            .setResponseCode(401)
            .setBody(RestServiceTestHelper.readStringFromFile(errorJson))

        errorInvalidResponse = MockResponse()
            .setResponseCode(404)
            .setBody(RestServiceTestHelper.readStringFromFile(errorInvalidJson))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testApiFailure() {
        mockWebServer.enqueue(errorResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 401 Client Error", errorResponse.status)
    }

    @Test
    fun testApiFailureInvalid() {
        mockWebServer.enqueue(errorInvalidResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        errorInvalidResponse.status

        Assert.assertEquals("HTTP/1.1 404 Client Error", errorInvalidResponse.status)
    }

    @Test
    fun testGenreResponseSuccess() {
        val genreJson = "genre_response.json"

        val myResponse = MockResponse()
            .setResponseCode(200)
            .setBody(RestServiceTestHelper.readStringFromFile(genreJson))

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMoviePopular() {
        val jsonValue = "movie_popular_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }


    @Test
    fun testMovieFavorite() {
        val jsonValue = "movie_favorite_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvPopular() {
        val jsonValue = "tv_popular_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvFavorite() {
        val jsonValue = "tv_favorite_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }


    @Test
    fun testMultiSearchSuccess() {
        val jsonValue = "multi_search_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMovieCredits() {
        val jsonValue = "movie_credits_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }


    @Test
    fun testMovieRated() {
        val jsonValue = "movie_rated_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testCastDetail() {
        val jsonValue = "cast_detail_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }


    @Test
    fun testPostSuccess() {
        val jsonValue = "post_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        Assert.assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }


    private fun setUpMockResponse(json: String) : MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .setBody(RestServiceTestHelper.readStringFromFile(json))
    }

}