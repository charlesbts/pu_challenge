package br.com.pu.pu_challenge.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {

        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        return objects.toList()
    }
}