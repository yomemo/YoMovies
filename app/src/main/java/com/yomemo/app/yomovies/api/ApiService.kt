package com.yomemo.yomovies.api

import com.yomemo.app.yomovies.BuildConfig
import com.yomemo.app.yomovies.api.TMDBEndpoints
import com.yomemo.yomovies.utils.AppConstants.TMDB.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val tmdbAuthInterceptor = Interceptor { chain ->
    val newUrl = chain.request().url
        .newBuilder()
//        .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
        .build()
    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()
    chain.proceed(newRequest)
}


object ApiService {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(tmdbAuthInterceptor)
        .build()
    private val tmdbBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getTMDBApi(): TMDBEndpoints = tmdbBuilder.create(TMDBEndpoints::class.java)
}