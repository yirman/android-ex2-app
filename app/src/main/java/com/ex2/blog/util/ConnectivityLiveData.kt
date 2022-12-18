package com.ex2.blog.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData

class ConnectivityLiveData constructor(private val connectivityManager: ConnectivityManager) : LiveData<Boolean>() {

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    constructor(application: Application) : this(application.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager)

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(true)
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val networkCapabilities = connectivityManager.activeNetwork
        var result = false
        if(networkCapabilities != null){
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)
            actNw?.let {
                result = when {
                    it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        postValue(result)
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}