package org.darihuan.imdbapi.searchFilmsFragment.model


import org.darihuan.imdbapi.FilmsPersistence
import org.darihuan.imdbapi.common.entities.Film
import org.darihuan.imdbapi.common.entities.Movie
import org.darihuan.imdbapi.common.entities.RequestResult
import org.darihuan.imdbapi.common.requests.APIMoshi

class SearchFilmsModel {




    suspend fun saveMovie(film: Film) {
        FilmsPersistence.database.filmDao()
            .addMovie(Movie(0, film.image, film.title, film.description))
    }
}
