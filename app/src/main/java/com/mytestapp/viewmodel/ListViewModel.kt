package com.mytestapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mytestapp.api.RestClient
import com.mytestapp.model.MovieListModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ListViewModel(application: Application) : AndroidViewModel(application) {
     private lateinit var heros: MutableLiveData<ArrayList<MovieListModel>>


    fun getHeros(): MutableLiveData<ArrayList<MovieListModel>> {

        if (!::heros.isInitialized) {
            heros = MutableLiveData()
            loadUsers()
        }

        return heros
    }

    private fun loadUsers() {
        RestClient.Companion.getInstance().service.getMovies
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<ArrayList<MovieListModel>?> {
                override fun onSubscribe(d: Disposable) {}


                override fun onError(e: Throwable) {
                    heros.value = null
                }

                override fun onComplete() {

                }

                override fun onNext(t: ArrayList<MovieListModel>) {
                    heros.value = t
                }
            })
    }

}

