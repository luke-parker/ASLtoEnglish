package com.example.asltoenglish

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.camera.core.VideoCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

class HomeFragment : BaseFragment(), LifecycleOwner {

    // Instance Variables

    private lateinit var viewFinder: TextureView
    private lateinit var captureButton: ImageButton
    private lateinit var videoCapture: VideoCapture

    // Constructors

    companion object Static {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun titleForActionBar(): String {
        return "Home"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewFinder = view.findViewById(R.id.view_finder)
        captureButton = view.findViewById(R.id.capture_button)

        // Request camera permissions
        if (allPermissionsGranted()) {
            viewFinder.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                activity!!, (activity as MainActivity).REQUIRED_PERMISSIONS, (activity as MainActivity).REQUEST_CODE_PERMISSIONS)
        }


        return view
    }

    // Helpers

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == (activity as MainActivity).REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                viewFinder.post { startCamera() }
            } else {
                Toast.makeText(context,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
//                finish()
            }
        }
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in (activity as MainActivity).REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    context!!, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun startCamera() {
        // Create configuration object for the viewfinder use case
        val previewConfig = PreviewConfig.Builder().build()
        // Build the viewfinder use case
        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            viewFinder.surfaceTexture = it.surfaceTexture
            parent.addView(viewFinder, 0)
        }

        // Bind use cases to lifecycle
        CameraX.bindToLifecycle(this, preview)
    }

}