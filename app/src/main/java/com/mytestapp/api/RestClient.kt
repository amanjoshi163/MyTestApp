package com.mytestapp.api

import android.app.Activity
import com.google.gson.Gson
import com.mytestapp.utils.AppConstant
import com.sk.distributor.api.APIServices

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by User on 03-11-2018.
 */
class RestClient {



    val service: APIServices
        get() = retrofit!!.create(APIServices::class.java)

    companion object {

        private var retrofit: Retrofit? = null

        private val ourInstance = RestClient()
        fun getInstance(): RestClient {

            return ourInstance
        }


    }





    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(Interceptor addInterceptor@{ chain: Interceptor.Chain ->
                var response: Response? = null
                try {
                    val request = chain.request()
                    response = chain.proceed(request)
                    if (response.code == 200) {

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return@addInterceptor response!!
            })

            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
//                .baseUrl(SharePrefs.getInstance(MyApplication.getInstance()!!.baseContext)!!.getStringBaseURL(SharePrefs.BASE_URL_TEST))
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }
    }


}