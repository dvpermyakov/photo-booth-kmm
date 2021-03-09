package com.dvpermyakov.photo.booth.androidApp.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.dvpermyakov.photo.booth.androidApp.InteractorProvider
import com.dvpermyakov.photo.booth.androidApp.Navigation
import com.dvpermyakov.photo.booth.androidApp.R
import java.io.ByteArrayOutputStream

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoButtonView = view.findViewById<AppCompatButton>(R.id.photoButtonView)
        photoButtonView.setOnClickListener {
            dispatchTakePictureIntent()
        }
        val watchPhotosView = view.findViewById<AppCompatButton>(R.id.watchPhotosView)
        watchPhotosView.setOnClickListener {
            (activity as Navigation).navigateToImagePicker()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            (activity as InteractorProvider)
                .getPhotoInteractor()
                .saveImage(IMAGE_NAME, toByteArray(imageBitmap))
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun toByteArray(bmp: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.WEBP, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()
        bmp.recycle()
        return byteArray
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1243
        private const val IMAGE_NAME = "imageName"
    }
}