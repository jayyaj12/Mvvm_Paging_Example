package com.example.mvvmexample

import com.example.mvvmexample.data.network.BookServiceApi
import com.example.mvvmexample.util.authorization.GetAuthorization
import com.example.mvvmexample.util.network.CustomCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class RetrofitUnitTest {

    private lateinit var server: MockWebServer
    private lateinit var bookServiceApi: BookServiceApi
    private lateinit var retrofit: Retrofit

    @Before
    fun setupTest() {
        server = MockWebServer()
        server.start()

        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .addConverterFactory(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true // 지정되지 않은 key 값은 무시
                    coerceInputValues = true // default 값 설정
                    explicitNulls = false // 없는 필드는 null로 설정
                }.asConverterFactory("application/json".toMediaType())
            ).build()

        bookServiceApi = retrofit.create(BookServiceApi::class.java)
    }

    @After
    fun testDown() = server.shutdown()

    @Test
    fun getSearchBookTest() = runTest {
        val response = """
            {
              "response": {
                "header": {
                  "resultCode": 0,
                  "resultMsg": "성공"
                },
                "body": {
                  "dataType": "JSON",
                  "items": {
                    "item": [
                      {
                        "baseDate": "20230426",
                        "baseTime": "0500",
                        "category": "T1H",
                        "fcstDate": "20230426",
                        "fcstTime": "0600",
                        "fcstValue": "10",
                        "nx": 60,
                        "ny": 127
                      }
                    ]
                  }
                }
              }
            }
        """.trimIndent()
        
        server.enqueue(MockResponse().setBody(response))
        
//        val book = bookServiceApi.getSearchBook(
//            "질문",
//            null,
//            null,
//            null,
//            null
//        )
//
//        assertNotNull(book)
//
//        assertEquals(book, 0)
//        assertEquals("성공", book.response.header.resultMsg)
//        assertEquals("JSON", book.response.body.dataType)
//        assertNotNull(book.body.items.item)

    }

}