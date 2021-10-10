package com.mytestapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mytestapp.R
import com.mytestapp.databinding.ActivityAddUserBinding
import com.mytestapp.model.User
import com.mytestapp.roomdb.UserDatabase
import com.mytestapp.viewmodel.AddUserViewModel
import com.mytestapp.viewmodel.ListViewModel


class AddUserActivity : AppCompatActivity(){
    private lateinit var mBinding: ActivityAddUserBinding
    private var appDatabase: UserDatabase? = null

    private var adduserModel: AddUserViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        adduserModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        mBinding.addUserModel = adduserModel
        mBinding.lifecycleOwner = this

        adduserModel!!.shouldCloseLiveData.observe(this, Observer { finish() })
        initialization()
    }

    private fun initialization() {
        appDatabase = UserDatabase.getInstance(this)

    }

}