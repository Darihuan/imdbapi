package org.darihuan.imdbapi.listFilmsFramgment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.darihuan.imdbapi.common.entities.Movie
import org.darihuan.imdbapi.listFilmsFramgment.model.ListFilmsModel

class ListFilmsViewModel:ViewModel() {
    private var model:ListFilmsModel = ListFilmsModel()
    private val movies = model.movies

    fun getAllMovies():LiveData<MutableList<Movie>> {
        return movies;
    }
    fun deleteMovie(movie:Movie){
        viewModelScope.launch {
            model.deleteMovie(movie)

        }
    }
    fun deleteAllMovies() {
        viewModelScope.launch {
            viewModelScope.launch {
                model.deleteAllMovies()
            }
        }
    }
}