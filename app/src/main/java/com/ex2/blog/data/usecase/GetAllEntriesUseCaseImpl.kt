package com.ex2.blog.data.usecase

import com.ex2.blog.domain.entity.reduceContent
import com.ex2.blog.domain.repository.EntryRepository
import com.ex2.blog.domain.usecase.GetAllEntriesUseCase
import com.ex2.blog.util.Resource
import kotlinx.coroutines.flow.transform
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

class GetAllEntriesUseCaseImpl @Inject constructor(
    private val repository: EntryRepository
): GetAllEntriesUseCase {

    override fun execute(refreshApi: Boolean) = repository.queryEntries(refreshApi).transform {
        it.data?.let { entries ->
            entries.forEach { entry ->
                entry.content = entry.reduceContent()
            }
        }
        emit(it)
    }
}