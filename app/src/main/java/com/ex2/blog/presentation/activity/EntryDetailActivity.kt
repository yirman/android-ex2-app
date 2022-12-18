package com.ex2.blog.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import com.ex2.blog.R
import com.ex2.blog.databinding.ActivityEntryDetailBinding
import com.ex2.blog.domain.entity.parseDate
import com.ex2.blog.domain.entity.parseTime
import com.ex2.blog.presentation.activity.base.BaseEntryActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class EntryDetailActivity : BaseEntryActivity() {

    private lateinit var binding: ActivityEntryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val entryId = intent.getStringExtra("entry_id")

        viewModel.entryDetail.observe(this){ entry ->
            entry?.let {
                title = it.title
                binding.tvAuthor.text = getString(R.string.by_author, it.author)
                binding.tvDate.text = getString(R.string.release_on, it.parseDate() + " " + it.parseTime())
                binding.tvContent.text = it.content
            }
        }

        viewModel.queryEntryDetail(entryId!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}