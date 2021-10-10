package com.mytestapp.activity

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mytestapp.R
import com.mytestapp.databinding.ActivityEditUserBinding
import com.mytestapp.roomdb.UserDatabase
import com.mytestapp.viewmodel.EditUserViewModel

class EditUserActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityEditUserBinding
    private var appDatabase: UserDatabase? = null
    private var id = 0
    private var edituserModel: EditUserViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user)
        edituserModel = ViewModelProvider(this).get(EditUserViewModel::class.java)
        mBinding.editViewModel = edituserModel
        mBinding.lifecycleOwner = this

        edituserModel!!.shouldCloseLiveData.observe(this, Observer { finish() })
        initialization()
    }

    private fun initialization() {
        appDatabase = UserDatabase.getInstance(this@EditUserActivity)

        val extras = intent.extras
        if (extras != null) {
            id = extras.getInt("id")
            val user = appDatabase!!.userDao().findById(id)
            edituserModel!!.setUserData(user)
            mBinding.editBookSpinner.setSelection(
                getIndex(
                    mBinding.editBookSpinner,
                    user!!.bookValue
                )
            )

        }
    }

    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {
                return i
            }
        }
        return 0
    }

}