package com.mytestapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytestapp.R
import com.mytestapp.adapter.UserAdapter
import com.mytestapp.databinding.ActivityTaskOneBinding
import com.mytestapp.interfaces.DeleteInterface
import com.mytestapp.model.User
import com.mytestapp.roomdb.UserDatabase
import com.mytestapp.viewmodel.AddUserViewModel
import com.mytestapp.viewmodel.ListViewModel
import com.mytestapp.viewmodel.UserViewModel
import java.util.*
import kotlin.collections.ArrayList

class TaskOneActivity : AppCompatActivity(), View.OnClickListener, DeleteInterface {
    private lateinit var mBinding: ActivityTaskOneBinding
    private var appDatabase: UserDatabase? = null
    private var userModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_one)
        userModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mBinding.userViewModel = userModel
        initialization()

    }

    private fun initialization() {
        appDatabase = UserDatabase.getInstance(this)
        mBinding.txtAddUser.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        setListView()
    }

    private fun setListView() {
        userModel?.getUsers()?.observe(this, androidx.lifecycle.Observer {
            if (it==null) {
                mBinding.recyclerMyUser.visibility = View.GONE
                mBinding.txtNoUser.visibility = View.VISIBLE
            } else {

                mBinding.recyclerMyUser.visibility = View.VISIBLE
                mBinding.txtNoUser.visibility = View.GONE
                val uAdapter = UserAdapter(this, it, this)
                val layoutManager = LinearLayoutManager(this)
                mBinding.recyclerMyUser.layoutManager = layoutManager
                mBinding.recyclerMyUser.adapter = uAdapter

            }
        })



    }

    override fun onClick(v: View) {
        if (v.id == R.id.txt_add_user) {
            startActivity(Intent(this, AddUserActivity::class.java))

        }
    }

    override fun deleteUser(user: User) {
        appDatabase!!.userDao().delete(user)
        setListView()
    }


}