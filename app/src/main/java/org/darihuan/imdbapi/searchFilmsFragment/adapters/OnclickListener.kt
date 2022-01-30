package org.darihuan.imdbapi.searchFilmsFragment.adapters

import org.darihuan.imdbapi.common.entities.Film

interface OnclickListener {
    fun OnAddMovie(film: Film)
}