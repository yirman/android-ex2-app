package com.ex2.blog.data.remote

import com.ex2.blog.domain.entity.Entry
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EntryService {

    @GET("entries")
    suspend fun requestEntries(): Response<List<Entry>>

    @POST("entry")
    suspend fun uploadEntry(@Body entry: Entry): Response<Entry>

}