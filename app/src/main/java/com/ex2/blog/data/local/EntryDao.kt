package com.ex2.blog.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ex2.blog.domain.entity.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: Entry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntries(entry: List<Entry>)

    @Query("SELECT * FROM entries")
    fun queryAllEntries(): Flow<List<Entry>>

    @Query("SELECT * FROM entries WHERE id = :id")
    fun queryEntry(id: String): Flow<Entry>

    @Query("SELECT * FROM entries WHERE title LIKE '%' || :search || '%' " +
            "OR content LIKE '%' || :search || '%' " +
            "OR author LIKE '%' || :search || '%'"
    )
    fun searchEntry(search: String): Flow<List<Entry>>

    @Query("DELETE FROM entries")
    fun deleteEntries()
}