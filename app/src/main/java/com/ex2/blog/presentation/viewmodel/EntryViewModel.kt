package com.ex2.blog.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.domain.usecase.GetAllEntriesUseCase
import com.ex2.blog.domain.usecase.GetEntryDetailUseCase
import com.ex2.blog.domain.usecase.SearchEntriesUseCase
import com.ex2.blog.domain.usecase.UploadEntryUseCase
import com.ex2.blog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class EntryViewModel @Inject constructor(
    private val getAllEntriesUseCase: GetAllEntriesUseCase,
    private val getEntryDetailUseCase: GetEntryDetailUseCase,
    private val searchEntriesUseCase: SearchEntriesUseCase,
    private val uploadEntryUseCase: UploadEntryUseCase
) : ViewModel() {

    private var entriesJob: Job? = null
    private val _entries = MutableLiveData<Resource<List<Entry>>>()
    val entries: LiveData<Resource<List<Entry>>> = _entries

    private var entriesSearchJob: Job? = null
    private val _entriesSearch = MutableLiveData<List<Entry>>()
    val entriesSearch: LiveData<List<Entry>> = _entriesSearch

    private var entryUploadJob: Job? = null
    private val _entryUpload = MutableLiveData<Resource<Entry>>()
    val entryUpload: LiveData<Resource<Entry>> = _entryUpload

    private var entryDetailJob: Job? = null
    private val _entryDetail = MutableLiveData<Entry>()
    val entryDetail: LiveData<Entry> = _entryDetail

    fun queryEntryDetail(id: String){
        entryDetailJob?.cancel()
        entryDetailJob = viewModelScope.launch {
            getEntryDetailUseCase.execute(id).cancellable().collect { entry ->
                _entryDetail.value = entry
            }
        }
    }

    fun queryEntries(refreshApi: Boolean = false){
        entriesJob?.cancel()
        entriesJob = viewModelScope.launch {
            getAllEntriesUseCase.execute(refreshApi).cancellable().collect { resource ->
                _entries.value = resource
            }
        }
    }

    fun searchEntries(search: String = ""){
        entriesSearchJob?.cancel()
        entriesSearchJob = viewModelScope.launch {
            searchEntriesUseCase.execute(search).cancellable().collect { result ->
                _entriesSearch.value = result
            }
        }
    }

    fun uploadEntry(entry: Entry) {
        entryUploadJob?.cancel()
        entryUploadJob = viewModelScope.launch {
            uploadEntryUseCase.execute(entry).cancellable().collect { resource ->
                _entryUpload.value = resource
            }
        }
    }

}