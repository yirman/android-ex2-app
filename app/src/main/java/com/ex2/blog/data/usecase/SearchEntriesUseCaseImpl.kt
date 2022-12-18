package com.ex2.blog.data.usecase

import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.domain.entity.reduceContent
import com.ex2.blog.domain.repository.EntryRepository
import com.ex2.blog.domain.usecase.SearchEntriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class SearchEntriesUseCaseImpl @Inject constructor(
    private val repository: EntryRepository
): SearchEntriesUseCase {
    override fun execute(search: String): Flow<List<Entry>> = repository.searchEntries(search).transform {
        it.forEach { entry ->
            entry.content = entry.reduceContent()
        }
        emit(it)
    }
}