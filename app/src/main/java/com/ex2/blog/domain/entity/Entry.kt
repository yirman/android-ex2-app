package com.ex2.blog.domain.entity

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.StringUtils
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "entries")
data class Entry (
	@PrimaryKey
	@SerializedName("id") val id : String = "",
	@SerializedName("author") var author : String? = null,
	@SerializedName("title") var title : String? = null,
	@SerializedName("content") var content : String? = null,
	@SerializedName("date") var date : Date? = null,
)

@SuppressLint("SimpleDateFormat")
fun Entry.parseDate(): String?{
	return date?.let{ SimpleDateFormat("dd MMM yyyy").format(it) }
}
@SuppressLint("SimpleDateFormat")
fun Entry.parseTime(): String?{
	return date?.let{ SimpleDateFormat("HH:mm").format(it) }
}

fun Entry.reduceContent(): String?{
	return content?.let { if(it.length > 70) StringUtils.substring(it,0, 70) + "..." else it }
}