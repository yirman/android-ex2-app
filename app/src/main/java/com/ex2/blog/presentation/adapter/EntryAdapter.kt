package com.ex2.blog.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ex2.blog.databinding.ItemEntryBinding
import com.ex2.blog.domain.entity.Entry
import com.ex2.blog.domain.entity.parseDate
import com.ex2.blog.domain.entity.parseTime

class EntryAdapter (private val listener: OnEntryClickListener): RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {

    private val items = mutableListOf<Entry>()

    fun setItems(items: List<Entry>){
        this.items.clear()
        this.items.addAll(items)
    }

    class EntryViewHolder(private val itemBinding: ItemEntryBinding, private val listener: OnEntryClickListener): RecyclerView.ViewHolder(itemBinding.root){

        @SuppressLint("SetTextI18n")
        fun bind (entry: Entry){
            itemBinding.tvTitle.text = entry.title
            itemBinding.tvContent.text = entry.content
            itemBinding.tvDate.text = "${entry.parseDate()} ${entry.parseTime()}"
            itemBinding.root.setOnClickListener {
                listener.onClickEntry(entry.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder =
        EntryViewHolder(ItemEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int)  = holder.bind(items[position])

    interface OnEntryClickListener{
        fun onClickEntry(id: String)
    }
}