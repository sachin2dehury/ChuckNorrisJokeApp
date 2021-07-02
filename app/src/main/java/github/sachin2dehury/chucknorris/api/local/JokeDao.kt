package github.sachin2dehury.chucknorris.api.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.chucknorris.api.remote.Joke
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: Joke)

    @Query("SELECT * FROM db")
    fun getJokes(): Flow<List<Joke>>

//    @Delete
//    fun deleteJoke(joke: Joke)

}