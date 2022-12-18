package com.ex2.blog.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ex2.blog.R
import com.ex2.blog.databinding.ActivityNewEntryBinding
import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.presentation.activity.base.BaseEntryActivity
import com.ex2.blog.presentation.dialog.LoadingDialogFragment
import com.ex2.blog.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.apache.commons.lang3.StringUtils


@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class NewEntryActivity : BaseEntryActivity(){

    private lateinit var binding: ActivityNewEntryBinding
    private var loadingDialog: LoadingDialogFragment? = null

    private var isOnline: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.compose)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.entryUpload.observe(this){

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loadingDialog?.dismiss()
                    Toast.makeText(this, getString(R.string.msg_entry_sent_correctly), Toast.LENGTH_LONG).show()
                    finish()
                }
                Resource.Status.ERROR -> {
                    loadingDialog?.dismiss()
                }
                Resource.Status.LOADING -> {
                    loadingDialog = LoadingDialogFragment()
                    loadingDialog!!.isCancelable = false
                    loadingDialog!!.show(supportFragmentManager, LoadingDialogFragment::class.java.name)
                }
            }
        }


        connectivityLiveData.observe(this){ isOnline ->
            this.isOnline = isOnline
            if(isOnline)
                binding.tvNoInternet.visibility = View.GONE
            else{
                binding.tvNoInternet.visibility = View.VISIBLE
            }
        }

    }

    private fun sendEntry(){


        val author = binding.author.text.toString()
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()

        if(StringUtils.isAllEmpty(author, title, content)){
            Toast.makeText(this, getString(R.string.msg_fill_fields), Toast.LENGTH_LONG).show()
        }
        else{


            if(isOnline){
                val entry = Entry(
                    author = author,
                    title = title,
                    content = content
                )
                viewModel.uploadEntry(entry)
            }
            else{
                Toast.makeText(this, getString(R.string.msg_internet_connection_error), Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.new_entry, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
             android.R.id.home -> {
                finish()
            }
            R.id.send -> {
                sendEntry()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}