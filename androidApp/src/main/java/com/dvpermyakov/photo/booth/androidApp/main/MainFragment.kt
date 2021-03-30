package com.dvpermyakov.photo.booth.androidApp.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dvpermyakov.photo.booth.androidApp.InteractorProvider
import com.dvpermyakov.photo.booth.androidApp.Navigation
import com.dvpermyakov.photo.booth.androidApp.databinding.FragmentMainBinding
import java.io.ByteArrayOutputStream

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.photoButtonView.setOnClickListener {
            dispatchTakePictureIntent()
        }
        binding.watchPhotosView.setOnClickListener {
            (activity as Navigation).navigateToImagePicker()
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            (activity as InteractorProvider)
                .getPhotoInteractor()
                .saveImage(System.currentTimeMillis().toString(), toByteArray(imageBitmap))
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
        val byteArray = stream.toByteArray()
        bmp.recycle()
        return byteArray
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1243
    }
}