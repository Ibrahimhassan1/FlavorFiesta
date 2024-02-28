package com.softups.flavorfiesta.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

// Attribute: https://stackoverflow.com/a/51083865/3323954
class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}