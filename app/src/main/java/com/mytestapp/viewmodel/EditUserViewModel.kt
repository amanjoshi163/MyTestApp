package com.mytestapp.viewmodel

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mytestapp.model.User
import com.mytestapp.roomdb.UserDatabase

class EditUserViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private var appDatabase: UserDatabase? = null
    val shouldCloseLiveData = MutableLiveData<Void>()
    var bookvalue = ""
    var id=0;
    var uName: MutableLiveData<String> = MutableLiveData()
    var uMobile: MutableLiveData<String> = MutableLiveData()


    fun setUserData(user: User?) {
        uName.value = user!!.name
        uMobile.value = user.mobile
        id= user.id!!
    }

    fun onClicked() {

        checkValidation()
    }

    private fun checkValidation() {
        appDatabase = UserDatabase.getInstance(context)
        val name = uName.value
        val mobile = uMobile.value


        if (name!!.isEmpty()) {
            Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show()
        } else if (mobile!!.isEmpty()) {

            Toast.makeText(context, "Please Enter Mobile Number", Toast.LENGTH_SHORT)
                .show()
        } else if (mobile.length <= 9) {

            Toast.makeText(context, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT)
                .show()
        } else {

            var user = User(name, mobile, bookvalue,id)
            appDatabase!!.userDao().update(user)
            Toast.makeText(context, "User Update Successfully", Toast.LENGTH_SHORT)
                .show()

            someAction()
        }

    }

    fun someAction() {
        shouldCloseLiveData.postValue(null)
    }

    fun onSelectItem(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        bookvalue = parent!!.selectedItem.toString()

    }
}