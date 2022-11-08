package io.hydok.composeviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hydok.composeviewmodel.api.RetrofitBuilder
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var movieListData:List<Movie> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getMovieList() {
        viewModelScope.launch {

            val apiService = RetrofitBuilder.getInstance()

            try {
                val movieList = apiService.getMovies()
                movieListData = movieList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}