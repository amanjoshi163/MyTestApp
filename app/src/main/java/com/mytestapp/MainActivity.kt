package com.mytestapp


import android.Manifest
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.mytestapp.activity.TaskOneActivity
import com.mytestapp.activity.TaskThreeActivity
import com.mytestapp.activity.TaskTwoActivity
import com.mytestapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initialization()
    }

    private fun initialization() {

        binding.taskBtn1.setOnClickListener(this)
        binding.taskBtn2.setOnClickListener(this)
        binding.taskBtn3.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.task_btn_1 -> {
                startActivity(Intent(this, TaskOneActivity::class.java))

            }
            R.id.task_btn_2 -> {
                startActivity(Intent(this, TaskTwoActivity::class.java))

            }
            R.id.task_btn_3 -> {
                startActivity(Intent(this, TaskThreeActivity::class.java))
                //requestWritePermission()

            }
        }
    }





}