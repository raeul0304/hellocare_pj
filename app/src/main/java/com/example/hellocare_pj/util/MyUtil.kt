package com.example.hellocare_pj.util

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date

fun myCheckPermission(activity: AppCompatActivity) {


    val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {

        if (it) {
            Toast.makeText(activity, "권한 승인", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "권한 거부", Toast.LENGTH_SHORT).show()

        }

    }
    requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
}

fun dateToString(date: Date): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}