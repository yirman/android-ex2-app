package com.ex2.blog.data.repository

import com.ex2.blog.data.local.EntryDao
import com.ex2.blog.data.remote.EntryRemoteDataSource
import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.domain.repository.EntryRepository
import com.ex2.blog.util.Resource
import com.ex2.blog.util.localNetworkBoundResource
import com.ex2.blog.util.uploadNetworkResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EntryRepositoryImpl @Inject constructor(
    private val remoteDataSource: EntryRemoteDataSource,
    private val localDataSource: EntryDao
): EntryRepository {

    override fun queryEntries(refreshApi: Boolean) = localNetworkBoundResource(
        query = {
            localDataSource.queryAllEntries()
        },
        shouldFetch = { entryList ->
            entryList.isEmpty() || refreshApi
        },
        fetch = {
            remoteDataSource.requestEntries()
        },
        onFetchCompleted = { resource ->
            if(resource.status == Resource.Status.SUCCESS)
                localDataSource.deleteEntries()
        },
        saveFetchResult = { resource ->
            resource.data?.let { entries ->
                localDataSource.insertEntries(entries)
            }
        }
    )

    override fun uploadEntry(entry: Entry): Flow<Resource<Entry>> = uploadNetworkResource(
        upload = {
            remoteDataSource.uploadEntry(entry)
        },
        saveUploadResult = { result ->
            result.data?.let { entry ->
                localDataSource.insertEntry(entry)
            }
        }
    )

    override fun queryEntryDetail(id: String): Flow<Entry> = localDataSource.queryEntry(id)

    override fun searchEntries(search: String): Flow<List<Entry>> = localDataSource.searchEntry(search)
}