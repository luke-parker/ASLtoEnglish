package com.example.asltoenglish

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // Constants

    val REQUEST_CODE_PERMISSIONS = 10
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    val tag = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navigateToHome()
    }

    // Navigation

    fun navigateToHome() {
        val homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit()
    }

}
