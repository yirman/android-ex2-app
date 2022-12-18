package com.ex2.blog.presentation.activity.base

import android.os.Bundle
import com.ex2.blog.presentation.adapter.EntryAdapter

abstract class BaseEntriesActivity : BaseEntryActivity(), EntryAdapter.OnEntryClickListener{

    protected lateinit var adapter: EntryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = EntryAdapter(this)
    }
}