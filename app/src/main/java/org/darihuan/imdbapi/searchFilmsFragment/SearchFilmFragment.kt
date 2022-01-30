package org.darihuan.imdbapi.searchFilmsFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.darihuan.imdbapi.BuildConfig
import org.darihuan.imdbapi.R
import org.darihuan.imdbapi.common.entities.Film
import org.darihuan.imdbapi.databinding.FragmentSecondBinding

import org.darihuan.imdbapi.searchFilmsFragment.adapters.OnclickListener
import org.darihuan.imdbapi.searchFilmsFragment.adapters.RVSearchFilmsAdapter
import org.darihuan.imdbapi.searchFilmsFragment.viewModel.SearchFilmsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SearchFilmFragment : Fragment(),OnclickListener {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //viewmmodel
    private lateinit var _searchFilmViewModel:SearchFilmsViewModel

    //recyclerview
    private lateinit var _adapter:RVSearchFilmsAdapter
    private lateinit var _gridlayout:GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setViewModel()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.title_second_fragment)
        binding?.btnConfirmar?.setOnClickListener {
            if(_binding?.inputTitle?.text?.isEmpty() == true)
                Toast.makeText(requireContext(),"Empty search term",Toast.LENGTH_SHORT).show()
            else
             _searchFilmViewModel.searchResults(_binding?.inputTitle?.text.toString().trim(),BuildConfig.apikey);
                _searchFilmViewModel.response.observe(
                    viewLifecycleOwner,{peliculas->
                        _adapter.submitList(peliculas.films)
                    }
                )



        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViewModel() {
        _searchFilmViewModel = ViewModelProvider(this)
            .get(SearchFilmsViewModel::class.java)

    }
    private fun setRecyclerView() {
        _adapter = RVSearchFilmsAdapter(this)
        _gridlayout = GridLayoutManager(getContext(), 1)

        _binding?.resultados?.apply {
            setHasFixedSize(true)
            layoutManager = _gridlayout
            adapter = _adapter
        }

    }

    override fun OnAddMovie(film: Film) {
        _searchFilmViewModel.saveFilm(film)
        Toast.makeText(requireContext(), "A film has been saved", Toast.LENGTH_SHORT).show()
        view?.findNavController()?.navigate(R.id.action_SecondFragment_to_FirstFragment)

    }

}