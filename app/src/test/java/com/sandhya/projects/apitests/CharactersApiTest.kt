package com.sandhya.projects.apitests

import android.content.Context
import android.os.Build
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.sandhya.projects.characters.data.CharactersApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class CharactersApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var charactersApi: CharactersApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        charactersApi = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/")).build().create(CharactersApi::class.java)
    }

    @Test
    fun testGetCharactersData() {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = charactersApi.getCharactersData()
            mockWebServer.takeRequest()

            Assert.assertEquals(true, response.body()!!.isEmpty())
        }
    }

    @Test
    fun testGetCharactersData_returnCharactersData() {
        val mockResponse = MockResponse()
        val context = InstrumentationRegistry.getInstrumentation().context
        val content = readFileResource("charactersResponse.json", context)
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = charactersApi.getCharactersData()
            mockWebServer.takeRequest()

            Assert.assertEquals(false, response.body()!!.isEmpty())
            Assert.assertEquals(402, response.body()!!.size)
        }
    }

    @Test
    fun testGetCharactersData_returnError() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error fetching data")
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val response = charactersApi.getCharactersData()
            mockWebServer.takeRequest()

            Assert.assertEquals(false, response.isSuccessful)
            Assert.assertEquals(404, response.code())
            Assert.assertEquals("Client Error", response.message())
            Assert.assertEquals("Error fetching data", response.errorBody()?.string())
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun readFileResource(fileName: String, context: Context): String {
        try {
            val assetManager = context.assets
            val inputStream = assetManager.open(fileName)

            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            val builder = StringBuilder()
            var line: String? = reader.readLine()
            while (line != null) {
                builder.append(line)
                line = reader.readLine()
            }

            return builder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }
}