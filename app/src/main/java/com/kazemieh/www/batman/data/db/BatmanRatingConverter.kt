package com.kazemieh.www.batman.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kazemieh.www.batman.data.db.entity.DbRating


@ProvidedTypeConverter
class BatmanRatingConverter {
   private val gson = Gson()

    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<DbRating> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<DbRating?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<DbRating?>?): String {
        return gson.toJson(myObjects)
    }
}
