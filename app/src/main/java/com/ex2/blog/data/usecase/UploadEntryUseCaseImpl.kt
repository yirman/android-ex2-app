package com.ex2.blog.data.usecase

import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.domain.repository.EntryRepository
import com.ex2.blog.domain.usecase.UploadEntryUseCase
import com.ex2.blog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadEntryUseCaseImpl @Inject constructor(
    private val repository: EntryRepository
): UploadEntryUseCase {
    override fun execute(entry: Entry): Flow<Resource<Entry>> = repository.uploadEntry(entry)
}