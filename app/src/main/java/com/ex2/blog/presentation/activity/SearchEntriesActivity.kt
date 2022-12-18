package com.ex2.blog.presentation.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex2.blog.R
import com.ex2.blog.databinding.ActivitySearchEntriesBinding
import com.ex2.blog.presentation.activity.base.BaseEntriesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class SearchEntriesActivity : BaseEntriesActivity() {

    private lateinit var binding: ActivitySearchEntriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchEntriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.rvEntries.layoutManager = LinearLayoutManager(this)
        binding.rvEntries.adapter = adapter
        viewModel.entriesSearch.observe(this){ result ->
            if (!result.isNullOrEmpty()) {
                adapter.setItems(ArrayList(result))
                binding.viewNoEntries.visibility = View.GONE
            } else {
                adapter.setItems(emptyList())
                binding.viewNoEntries.visibility = View.VISIBLE
            }
            adapter.notifyDataSetChanged()
        }.also {
            viewModel.searchEntries()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchViewItem.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchEntries(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun onClickEntry(id: String) {

    }
}