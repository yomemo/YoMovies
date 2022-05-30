package com.yomemo.app.yomovies.api

import com.yomemo.yomovies.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBEndpoints {
    //  Get Now Playing Movies
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page") page: Int? = null,
        @Query("language") language: String? = "en-US",
        @Query("region") region: String? = null,
    ): Call<Movies>

//    //  Get Trending Movies /trending/{media_type}/{time_window}
//    @GET("/trending/movie/week")
//    fun getTrendingMovies(): Call<Movies>

    //  Get Upcoming Movies /movie/upcoming
    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("page") page: Int? = null,
        @Query("language") language: String? = "en-US",
        @Query("region") region: String? = null,
    ): Call<Movies>


    //  Get Popular Movies  /movie/popular
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int? = null,
        @Query("language") language: String? = "en-US",
        @Query("region") region: String? = null,
    ): Call<Movies>

    // Get Top Rated Movies  /movie/top_rated
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int? = null,
        @Query("language") language: String? = "en-US",
        @Query("region") region: String? = null,
    ): Call<Movies>

    // Get Latest Movies /movie/latest
    @GET("movie/latest")
    fun getLatestMovie(
        @Query("page") page: Int? = null,
        @Query("language") language: String? = "en-US",
        @Query("region") region: String? = null,
    ): Call<Movie>

    // Get Latest Movies /movie/latest
    @GET("discover/movie")
    fun get2021Movies(
        @Query("page") page: Int? = null,
        @Query("primary_release_year") primary_release_year: String? = "2021",
        @Query("certification_country") region: String? = "US",
        @Query("sort_by") sort_by: String? = "popularity.desc",
    ): Call<Movies>


    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movie_id: String,
    ): Call<Movie>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movie_id: String): Call<Credits>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") movie_id: String): Call<Videos>


    @GET("movie/{movie_id}/reviews")
    fun getVideoReviews(@Path("movie_id") movie_id: String): Call<Reviews>

//
//    @GET("genre/movie/list")
//    fun getGenre(): Call<GenresResponse>


}