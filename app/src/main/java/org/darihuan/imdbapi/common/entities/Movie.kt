package org.darihuan.imdbapi.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey(autoGenerate = true)
    var id:Long =0,
    var image: String,
    var title: String,
    var description: String)
{
constructor():this(image = "",title = "",description = "")
}