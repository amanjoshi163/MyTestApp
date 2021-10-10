package com.mytestapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytestapp.R
import com.mytestapp.adapter.MovieAdapter
import com.mytestapp.databinding.ActivityTaskTwoBinding
import com.mytestapp.model.MovieListModel
import com.mytestapp.utils.Utils
import com.mytestapp.viewmodel.ListViewModel
import android.app.ProgressDialog




class TaskTwoActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityTaskTwoBinding
    private var lvModel: ListViewModel? = null
    private var adapter: MovieAdapter? = null
    lateinit var utils: Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_two)
        lvModel = ViewModelProvider(this).get(ListViewModel::class.java)
        mBinding.listModel = lvModel
        mBinding.lifecycleOwner = this
        initiliaze()
        callLitApi()

    }

    private fun initiliaze() {
        utils = Utils(this)
    }

    private fun callLitApi() {


        if (utils.isNetworkAvailable()) {
            val progressBar = ProgressDialog(this)
            progressBar.setCancelable(true) //you can cancel it by pressing back button
            progressBar.setMessage("Please wait ...")
            progressBar.show() //displays the progress bar

            lvModel?.getHeros()?.observe(this, Observer {

                if (progressBar.isShowing){
                   progressBar.dismiss()
                }

                if (it != null) {
                    setListAdapter(it)
                }else{
                    Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }

    private fun setListAdapter(it: ArrayList<MovieListModel>) {
        val layoutManager = LinearLayoutManager(this)
        mBinding.rvList.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        mBinding.rvList.addItemDecoration(dividerItemDecoration)
        adapter = MovieAdapter( it)
        mBinding.rvList.adapter = adapter


    }
}