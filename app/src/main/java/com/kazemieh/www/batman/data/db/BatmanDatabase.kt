package com.kazemieh.www.batman.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kazemieh.www.batman.data.db.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(BatmanRatingConverter::class)
abstract class BatmanDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}