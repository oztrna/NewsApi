package com.oztrna.newsapi.network

import com.oztrna.newsapi.model.Article
import com.oztrna.newsapi.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "d54b713cc2fd47f4ba077861cf517268"

interface MyApi {

    @GET("everything")
    fun getNews(
        @Query("q") search: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): Call<News>

    /*
    @GET("everything{q}{page}{apiKey}")
    suspend fun getNews(
        @Path("q") search: String,
        @Path("page") page: String,
        @Path("apiKey") apiKey: String
    ): Response<NewsResponse>

    companion object {
        operator fun invoke() : MyApi {

            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v2/")
                //.baseUrl("https://newsapi.org/v2/everything?q=besiktas&page=1&apiKey=${API_KEY}")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    } */
}