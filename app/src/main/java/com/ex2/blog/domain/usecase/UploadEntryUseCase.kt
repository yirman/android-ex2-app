package com.ex2.blog.domain.usecase

import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.util.Resource
import kotlinx.coroutines.flow.Flow

interface UploadEntryUseCase {

    fun execute(entry: Entry): Flow<Resource<Entry>>

}