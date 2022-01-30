package org.darihuan.imdbapi.listFilmsFramgment.adapters

import org.darihuan.imdbapi.common.entities.Movie

interface OnclickListener {
    fun onDeleteMovie(movie: Movie)
}