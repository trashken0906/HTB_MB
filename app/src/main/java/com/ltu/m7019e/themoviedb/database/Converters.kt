package com.ltu.m7019e.themoviedb.database

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromGenreList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGenreList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}