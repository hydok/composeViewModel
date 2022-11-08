package io.hydok.composeviewmodel.api

import io.hydok.composeviewmodel.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    suspend fun getMovies() : List<Movie>
}