package org.darihuan.imdbapi

import android.app.Application
import androidx.room.Room
import org.darihuan.imdbapi.common.dataBase.FilmDB

class FilmsPersistence:Application() {
    companion object {
        lateinit var database:FilmDB
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, FilmDB::class.java,"peliculas")
            .build()

    }
}