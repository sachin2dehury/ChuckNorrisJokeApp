package github.sachin2dehury.chucknorris.api.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.sachin2dehury.chucknorris.api.remote.Joke

@Database(
    entities = [Joke::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class JokeDataBase : RoomDatabase() {
    abstract fun getJokeDao(): JokeDao
}