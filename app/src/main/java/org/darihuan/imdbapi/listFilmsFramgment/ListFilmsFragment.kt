package org.darihuan.imdbapi.listFilmsFramgment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.darihuan.imdbapi.R
import org.darihuan.imdbapi.common.entities.Movie
import org.darihuan.imdbapi.databinding.FragmentFirstBinding
import org.darihuan.imdbapi.listFilmsFramgment.adapters.OnclickListener
import org.darihuan.imdbapi.listFilmsFramgment.adapters.RVListFilmAdapter
import org.darihuan.imdbapi.listFilmsFramgment.viewModel.ListFilmsViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFilmsFragment : Fragment(),OnclickListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //viewmodels
    private lateinit var _viewModel:ListFilmsViewModel

    //recyclerview
    private lateinit var _adapter:RVListFilmAdapter
    private lateinit var _gridlayout:GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.title_first_fragment)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setViewModel()
        _binding?.fab?.visibility = View.VISIBLE

        binding?.fab?.setOnClickListener {
            view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            binding.fab.visibility = View.GONE
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.borrar_icon ->{
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("¿Seguro que quieres Borrar todas las Peliculas?")
                    .setPositiveButton("Borrar",
                        { dialogInterface, i -> _viewModel.deleteAllMovies() })
                    .setNegativeButton("Cancelar", null).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteMovie(movie: Movie) {
        _viewModel.deleteMovie(movie)
        Toast.makeText(requireContext(), "Pelicula Borrada", Toast.LENGTH_SHORT).show()
    }

    private fun setViewModel() {
        _viewModel = ViewModelProvider(this)
            .get(ListFilmsViewModel::class.java)
        _viewModel.getAllMovies().observe(viewLifecycleOwner, { movies->
            _adapter.submitList(movies)
        })
    }
    private fun setRecyclerView() {
        _adapter = RVListFilmAdapter(this)
        _gridlayout = GridLayoutManager(getContext(), 1)

        _binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = _gridlayout
            adapter = _adapter
        }

    }
    override fun onPrepareOptionsMenu(menu: Menu){
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.borrar_icon)
        item.isVisible = true
    }

}