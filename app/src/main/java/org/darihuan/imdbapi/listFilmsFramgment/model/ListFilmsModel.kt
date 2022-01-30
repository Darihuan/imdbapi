package org.darihuan.imdbapi.listFilmsFramgment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import org.darihuan.imdbapi.FilmsPersistence
import org.darihuan.imdbapi.common.entities.Movie

class ListFilmsModel {
    val movies: LiveData<MutableList<Movie>> = liveData {
        val moviesLiveData = FilmsPersistence.database.filmDao().getAllMovies()
        emitSource(moviesLiveData.map { peliculas->
            peliculas.sortedBy { it.title }.toCollection(mutableListOf())
        })
    }
    suspend fun deleteAllMovies() {
        FilmsPersistence.database.filmDao().deleteAllMovies()
    }
    suspend fun deleteMovie(movie: Movie) {
        FilmsPersistence.database.filmDao().deleteMovie(movie)
    }
}