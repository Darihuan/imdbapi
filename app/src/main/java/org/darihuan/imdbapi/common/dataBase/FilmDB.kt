package org.darihuan.imdbapi.common.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import org.darihuan.imdbapi.common.entities.Movie

@Database(entities = arrayOf(Movie::class),version = 1,exportSchema = false)
abstract class FilmDB:RoomDatabase() {
    abstract fun filmDao():FilmDao
}