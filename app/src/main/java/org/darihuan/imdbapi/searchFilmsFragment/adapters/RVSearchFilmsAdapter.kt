package org.darihuan.imdbapi.searchFilmsFragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.darihuan.imdbapi.R
import org.darihuan.imdbapi.common.entities.Film
import org.darihuan.imdbapi.common.entities.Movie
import org.darihuan.imdbapi.databinding.SearchCardBinding


class RVSearchFilmsAdapter(private var listener:OnclickListener) : ListAdapter<Film, RecyclerView.ViewHolder>(FilmDiffCallBack()) {
    private lateinit var _Context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        _Context = parent.context
        val view = LayoutInflater.from(_Context).inflate(R.layout.search_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val film = getItem(position)
        with(holder as ViewHolder) {
                setListener(film)
                "${film.title}\n ${film.description}".also { binding.info.text = it }
            }

        }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SearchCardBinding.bind(view)
        fun setListener(film: Film) {
            with(binding.root) {
                setOnClickListener { listener.OnAddMovie(film) }
            }
        }
    }
}
class FilmDiffCallBack: DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.equals(newItem)
    }

}