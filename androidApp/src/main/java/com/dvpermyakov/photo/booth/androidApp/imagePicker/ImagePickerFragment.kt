package com.dvpermyakov.photo.booth.androidApp.imagePicker

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dvpermyakov.photo.booth.androidApp.InteractorProvider
import com.dvpermyakov.photo.booth.androidApp.R
import com.dvpermyakov.photo.booth.androidApp.databinding.FragmentImagePickerBinding

class ImagePickerFragment : Fragment(R.layout.fragment_image_picker) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImagePickerBinding.inflate(inflater)
        val byteArray = (activity as InteractorProvider)
            .getPhotoInteractor()
            .getImages(listOf("imageName"))
            .first()
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        binding.imageView.setImageBitmap(bitmap)
        return binding.root
    }

}