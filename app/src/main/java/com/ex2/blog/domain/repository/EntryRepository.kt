package com.ex2.blog.domain.repository

import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.util.Resource
import kotlinx.coroutines.flow.Flow

interface EntryRepository {

    fun queryEntries(refreshApi: Boolean = false): Flow<Resource<List<Entry>>>

    fun uploadEntry(entry: Entry): Flow<Resource<Entry>>

    fun queryEntryDetail(id: String): Flow<Entry>

    fun searchEntries(search: String): Flow<List<Entry>>
}