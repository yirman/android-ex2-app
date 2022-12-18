package com.ex2.blog.domain.usecase

import com.ex2.blog.domain.entity.Entry
import kotlinx.coroutines.flow.Flow

interface SearchEntriesUseCase {
    fun execute(search: String): Flow<List<Entry>>
}