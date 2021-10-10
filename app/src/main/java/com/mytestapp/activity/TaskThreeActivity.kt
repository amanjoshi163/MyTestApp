package com.mytestapp.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mytestapp.R
import com.mytestapp.databinding.ActivityTask3Binding
import com.mytestapp.viewmodel.DownLoadPDFViewModel

class TaskThreeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityTask3Binding

    private var viewModelPDF: DownLoadPDFViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_task3)
        viewModelPDF = ViewModelProvider(this).get(DownLoadPDFViewModel::class.java)
        mBinding.pdfviewmodel = viewModelPDF
        mBinding.lifecycleOwner = this
        viewModelPDF!!.progress.value = 8
        requestWritePermission()


    }

    private fun requestWritePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                101
            )
        }
    }
}