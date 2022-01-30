package org.darihuan.imdbapi.searchFilmsFragment.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.darihuan.imdbapi.common.entities.Film
import org.darihuan.imdbapi.common.entities.RequestResult
import org.darihuan.imdbapi.common.requests.APIMoshi
import org.darihuan.imdbapi.searchFilmsFragment.model.SearchFilmsModel

class SearchFilmsViewModel: ViewModel() {
    private val _response = MutableLiveData<RequestResult>()
    val response: LiveData<RequestResult>
        get() = _response
    private  var model:SearchFilmsModel = SearchFilmsModel()


     fun searchResults(filter:String, code:String) {
         viewModelScope.launch {
                _response.value = APIMoshi.retrofitMoshiService.getMovies(code, filter)
             }
         }

    fun saveFilm(film:Film) {
        viewModelScope.launch {
            model.saveMovie(film)
        }
    }

}