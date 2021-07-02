package github.sachin2dehury.chucknorris.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.sachin2dehury.chucknorris.api.local.JokeDao
import github.sachin2dehury.chucknorris.api.remote.Joke
import github.sachin2dehury.chucknorris.api.remote.JokeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jokeApi: JokeApi,
    private val jokeDao: JokeDao
) : ViewModel() {

    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> = _joke

    fun getJoke() = viewModelScope.launch {
        val response = jokeApi.getJokes()
        if (response.isSuccessful && response.code() == 200) {
            _joke.postValue(response.body())
            jokeDao.insertJoke(response.body()!!)
        }
    }

    fun postJoke(joke: Joke) = _joke.postValue(joke)

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    fun getJokes() = viewModelScope.launch {
        val result = jokeDao.getJokes().first()
        _jokes.postValue(result)
    }
}