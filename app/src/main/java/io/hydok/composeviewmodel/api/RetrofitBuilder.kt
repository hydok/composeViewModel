package io.hydok.composeviewmodel.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Builder
 * @author hydok
 * @version 1.0.0
 * @since 2021/09/15 1:23 오후
 **/
object RetrofitBuilder {

    var apiService: ApiService? = null
    fun getInstance() : ApiService {
        if (apiService == null) {
            apiService = Retrofit.Builder()
                .baseUrl("https://howtodoandroid.com/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
        return apiService!!
    }
}