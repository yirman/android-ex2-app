package com.ex2.blog.data.usecase

import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.domain.repository.EntryRepository
import com.ex2.blog.domain.usecase.GetEntryDetailUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntryDetailUseCaseImpl @Inject constructor(
    private val repository: EntryRepository
): GetEntryDetailUseCase{

    override fun execute(id: String): Flow<Entry> = repository.queryEntryDetail(id)

}