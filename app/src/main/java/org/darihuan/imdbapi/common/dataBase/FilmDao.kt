package org.darihuan.imdbapi.common.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.darihuan.imdbapi.common.entities.Movie

@Dao
interface FilmDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<MutableList<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id:String):Movie

    @Query("DELETE FROM movie")
    suspend fun  deleteAllMovies()
    @Insert
    suspend fun addMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)


}