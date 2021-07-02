package github.sachin2dehury.chucknorris.api.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromList(list: List<String>) = Gson().toJson(list)

    @TypeConverter
    fun toList(string: String): List<String> =
        Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)
}