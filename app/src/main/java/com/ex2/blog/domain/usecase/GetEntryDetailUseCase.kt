package com.ex2.blog.domain.usecase

import com.ex2.blog.domain.entity.Entry
import kotlinx.coroutines.flow.Flow

interface GetEntryDetailUseCase {

    fun execute(id: String): Flow<Entry>

}