package com.ex2.blog.data.remote

import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.util.RemoteDataSource
import javax.inject.Inject

class EntryRemoteDataSource @Inject constructor(
    var entryService: EntryService
): RemoteDataSource() {

    suspend fun requestEntries() = getResult { entryService.requestEntries() }

    suspend fun uploadEntry(entry: Entry) = getResult { entryService.uploadEntry(entry) }

}