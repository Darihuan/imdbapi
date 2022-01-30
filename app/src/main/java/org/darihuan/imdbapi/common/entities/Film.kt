package org.darihuan.imdbapi.common.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Film(
    var id: String,
    var resultType: String,
    var image: String,
    var title: String,
    var description: String
)