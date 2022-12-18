package com.ex2.blog.di

import com.ex2.blog.data.usecase.GetAllEntriesUseCaseImpl
import com.ex2.blog.data.usecase.GetEntryDetailUseCaseImpl
import com.ex2.blog.data.usecase.SearchEntriesUseCaseImpl
import com.ex2.blog.data.usecase.UploadEntryUseCaseImpl
import com.ex2.blog.domain.repository.EntryRepository
import com.ex2.blog.domain.usecase.GetAllEntriesUseCase
import com.ex2.blog.domain.usecase.GetEntryDetailUseCase
import com.ex2.blog.domain.usecase.SearchEntriesUseCase
import com.ex2.blog.domain.usecase.UploadEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllEntriesUseCase(entryRepository: EntryRepository): GetAllEntriesUseCase = GetAllEntriesUseCaseImpl(entryRepository)

    @Singleton
    @Provides
    fun provideGetEntryDetailUseCase(entryRepository: EntryRepository): GetEntryDetailUseCase = GetEntryDetailUseCaseImpl(entryRepository)

    @Singleton
    @Provides
    fun provideSearchEntriesUseCase(entryRepository: EntryRepository): SearchEntriesUseCase = SearchEntriesUseCaseImpl(entryRepository)

    @Singleton
    @Provides
    fun provideUploadEntryUseCaseImpl(entryRepository: EntryRepository): UploadEntryUseCase = UploadEntryUseCaseImpl(entryRepository)

}