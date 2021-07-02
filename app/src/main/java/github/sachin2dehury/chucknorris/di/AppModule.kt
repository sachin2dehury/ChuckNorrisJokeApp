package github.sachin2dehury.chucknorris.di

import android.content.Context
import androidx.room.Room
import github.sachin2dehury.chucknorris.BASE_URL
import github.sachin2dehury.chucknorris.api.local.JokeDataBase
import github.sachin2dehury.chucknorris.api.remote.JokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesJokeApi(): JokeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JokeApi::class.java)

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        JokeDataBase::class.java,
        "joke_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesJokeDao(jokeDataBase: JokeDataBase) = jokeDataBase.getJokeDao()
}