package com.ex2.blog.presentation.activity.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ex2.blog.presentation.viewmodel.EntryViewModel
import com.ex2.blog.util.ConnectivityLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseEntryActivity : AppCompatActivity() {
    protected val viewModel: EntryViewModel by viewModels()
    protected lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(application)
    }
}