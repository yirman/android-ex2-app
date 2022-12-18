package com.ex2.blog.di

import com.ex2.blog.data.local.EntryDao
import com.ex2.blog.data.remote.EntryRemoteDataSource
import com.ex2.blog.data.repository.EntryRepositoryImpl
import com.ex2.blog.domain.repository.EntryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEntryRepository(localDataSource: EntryDao, remoteDataSource: EntryRemoteDataSource): EntryRepository = EntryRepositoryImpl(remoteDataSource, localDataSource)
}