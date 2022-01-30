package org.darihuan.imdbapi.common.requests

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.darihuan.imdbapi.common.entities.RequestResult
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDBRequests {

    @GET("SearchMovie/{key}/{expression}")
    suspend fun getMovies(@Path("key") key:String, @Path("expression")expression:String):RequestResult
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val retrofitMoshi = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://imdb-api.com/en/API/")
    .build()
object APIMoshi {
    val retrofitMoshiService: IMDBRequests by lazy {
        retrofitMoshi.create(IMDBRequests::class.java)
    }
}