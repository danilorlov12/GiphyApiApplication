package com.example.giphyapiapplication

import com.example.giphyapiapplication.model.GifList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryService {

    @GET("search?api_key=dc6zaTOxFJmzC&limit=10&q=gif")
    fun getAffectedCountryList(): Call<GifList>
}

object ServiceBuilder {
    private const val URL = "https://api.giphy.com/v1/gifs/"
    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}