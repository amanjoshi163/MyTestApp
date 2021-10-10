package com.mytestapp.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    var context: Context? = null


    constructor(context: Context?) {
        this.context = context
        // setContext1(context)
    }


    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}