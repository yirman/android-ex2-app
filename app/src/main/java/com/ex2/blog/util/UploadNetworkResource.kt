package com.ex2.blog.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.channelFlow

@ExperimentalCoroutinesApi
inline fun <RequestType> uploadNetworkResource(
    crossinline upload: suspend () -> Resource<RequestType>,
    crossinline saveUploadResult: suspend (Resource<RequestType>) -> Unit,
    crossinline onUploadSuccess: () -> Unit = { },
    crossinline onUploadFailed: (Throwable) -> Unit = { }
) = channelFlow {

    send(Resource.loading())
    try{
        val response = upload()
        onUploadSuccess()
        saveUploadResult(response)
        send(response)
    }catch (t: Throwable) {
        onUploadFailed(t)
        send(Resource.error(t.message!!))
    }
}