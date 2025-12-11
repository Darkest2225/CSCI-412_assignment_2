package com.example.greetingscard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import java.io.File

class ThirdActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var openCamera: Button
    private lateinit var clickedImage: ImageView

    // Variables to hold the file and its Uri
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri

    // Unique request code for identifying the camera intent result
    private val CAMERA_REQUEST_CODE = 1001

    // Unique request code for requesting camera permission
    private val CAMERA_PERMISSION_CODE = 2001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        openCamera = findViewById(R.id.camera_open)
        clickedImage = findViewById(R.id.click_image)

        openCamera.setOnClickListener {
            // Check for CAMERA permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch camera
                launchCamera()
            } else {
                // Request CAMERA permission
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }
        }
    }

    // Function to launch the camera after permission is granted
    private fun launchCamera() {
        // Create a temporary image file to store the captured photo
        photoFile = createImageFile()

        // Get a content URI for the file using FileProvider
        // This allows secure sharing of the file with the camera app
        photoUri = FileProvider.getUriForFile(
            this,
            "com.example.greetingscard.fileprovider", // FileProvider authority (defined in manifest)
            photoFile
        )

        // Create an intent to launch the camera app
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            // Tell the camera app where to save the image
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            // Grant temporary write permission to the camera app for the URI
            addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }

        // Start the camera app and wait for the result
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to create a temporary image file in the cache directory
    private fun createImageFile(): File {
        // Create a directory inside cache to store images (if not already created)
        val imageDir = File(cacheDir, "images").apply { mkdirs() }
        // Create a temp file with prefix "captured_" and suffix ".jpg"
        return File.createTempFile("captured_", ".jpg", imageDir)
    }

    // Deprecated but still usable for handling activity results (like camera)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if the result is from the camera and was successful
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Load the captured image from the URI into the ImageView using Glide
            Glide.with(this).load(photoUri).into(clickedImage)
        }
    }
}
