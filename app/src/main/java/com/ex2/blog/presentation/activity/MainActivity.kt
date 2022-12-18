package com.ex2.blog.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex2.blog.R
import com.ex2.blog.databinding.ActivityMainBinding
import com.ex2.blog.presentation.activity.base.BaseEntriesActivity
import com.ex2.blog.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : BaseEntriesActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvEntries.layoutManager = LinearLayoutManager(this)
        binding.rvEntries.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.queryEntries(true)
        }

        binding.fabAddEntry.setOnClickListener {
            startActivity(Intent(this, NewEntryActivity::class.java))
        }

        viewModel.entries.observe(this){

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    if (!it.data.isNullOrEmpty()) {
                        adapter.setItems(ArrayList(it.data))
                        binding.tvNoEntries.visibility = View.GONE
                    } else {
                        adapter.setItems(emptyList())
                        binding.tvNoEntries.visibility = View.VISIBLE
                    }
                    adapter.notifyDataSetChanged()
                }
                Resource.Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                Resource.Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }.also {
            viewModel.queryEntries(false)
        }

        connectivityLiveData.observe(this){ isConnected ->
            if(isConnected)
                binding.tvNoInternet.visibility = View.GONE
            else{
                binding.tvNoInternet.visibility = View.VISIBLE
            }
        }
    }

    override fun onClickEntry(id: String) {
        val intent = Intent(this, EntryDetailActivity::class.java).apply {
            putExtra("entry_id", id)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.search -> {
                startActivity(Intent(this, SearchEntriesActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}