package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.database.Dao
import com.example.notesapp.data.database.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBase::class.java, "notes_database").build()

    @Provides
    fun provideDao(db: DataBase): Dao = db.getDao()
}