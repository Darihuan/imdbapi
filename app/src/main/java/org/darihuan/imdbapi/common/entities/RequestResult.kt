package org.darihuan.imdbapi.common.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestResult(
    var searchType:String,
    var expression:String,
    @Json(name = "results")  var films:List<Film>,
    var errorMessage:String)