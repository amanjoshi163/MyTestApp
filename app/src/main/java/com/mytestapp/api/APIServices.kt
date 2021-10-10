package com.sk.distributor.api


import com.mytestapp.model.MovieListModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by User on 03-11-2018.
 */
interface APIServices {


    @get:GET("marvel")
    val getMovies: Observable<ArrayList<MovieListModel>>?



}