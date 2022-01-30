package org.darihuan.imdbapi.listFilmsFramgment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.darihuan.imdbapi.R
import org.darihuan.imdbapi.common.entities.Movie
import org.darihuan.imdbapi.databinding.FilmCardBinding

class RVListFilmAdapter(private var listener:OnclickListener) :ListAdapter<Movie,RecyclerView.ViewHolder>(MovieDiffCallBack()){
    private lateinit var _Context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        _Context = parent.context
        val view = LayoutInflater.from(_Context).inflate(R.layout.film_card,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        with(holder as ViewHolder) {
            setListener(movie)
            "${movie.title}\n${movie.description}".also {
                binding.info.text = it
            }

            com.bumptech.glide.Glide.with(_Context)
                .load(movie.image)
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.movieimg)

        }
    }
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val binding = FilmCardBinding.bind(view)
            fun setListener(movie:Movie) {
                with(binding.root) {
                    setOnClickListener { listener.onDeleteMovie(movie) }
                }
            }
        }
}
class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.equals(newItem)
    }

}