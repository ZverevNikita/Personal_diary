package com.example.notesapp.di

import com.example.notesapp.data.RepositoryImpl
import com.example.notesapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepModule {
    @Binds
    abstract fun getRepository(impl: RepositoryImpl): Repository
}