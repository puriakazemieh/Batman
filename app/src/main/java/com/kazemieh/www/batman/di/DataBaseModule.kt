package com.kazemieh.www.batman.di

import android.content.Context
import androidx.room.Room
import com.kazemieh.www.batman.data.db.BatmanDatabase
import com.kazemieh.www.batman.data.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideRoutaaDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, BatmanDatabase::class.java, "batman.db")
            .allowMainThreadQueries()
            .build()



    @Provides
    @Singleton
    fun provideCartDao(
        database: BatmanDatabase
    ): MovieDao = database.movieDao()
}