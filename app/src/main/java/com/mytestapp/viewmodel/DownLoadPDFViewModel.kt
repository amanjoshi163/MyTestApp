package com.mytestapp.viewmodel

import android.app.Application
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class DownLoadPDFViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    var progress = MutableLiveData<Int>()

    val pdfUrl: MutableLiveData<String> = MutableLiveData()


    fun onClicked() {


        if (pdfUrl.value==null) {
            Toast.makeText(context, "Please Enter PDF Url", Toast.LENGTH_SHORT).show()
        } else {
            progress.value = 0; //View.VISIBLE
            val url = pdfUrl.value.toString()
            callpdf(url)
        }
    }


    private fun callpdf(url: String) {
        val time = System.currentTimeMillis()
        var destination =
            Environment.getExternalStorageDirectory().toString() + "/Aman_test_pdf/"
        val fileName = "Aman_test_" + time + ".pdf"
        destination += fileName

        val uri: Uri = Uri.parse("file://$destination")


        val request = DownloadManager.Request(Uri.parse(url))
        request.setDescription("Downloading....")
        request.setTitle("Aman_test_" + time)
        request.setDestinationUri(uri)

        val manager =
            context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = manager.enqueue(request)
        val finalDestination = destination
        val onComplete: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(ctxt: Context?, intent: Intent?) {


                context.unregisterReceiver(this)
                progress.value=8
                Toast.makeText(
                    context,
                    "PDF download Successfully ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}