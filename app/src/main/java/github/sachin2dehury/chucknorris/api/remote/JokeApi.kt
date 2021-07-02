package github.sachin2dehury.chucknorris.api.remote

import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {

    @GET("random")
    suspend fun getJokes(): Response<Joke>
}