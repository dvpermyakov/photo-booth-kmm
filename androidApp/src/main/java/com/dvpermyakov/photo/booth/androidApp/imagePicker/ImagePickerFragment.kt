package com.dvpermyakov.photo.booth.androidApp.imagePicker

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.dvpermyakov.photo.booth.androidApp.InteractorProvider
import com.dvpermyakov.photo.booth.androidApp.R

class ImagePickerFragment : Fragment(R.layout.fragment_image_picker) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<AppCompatImageView>(R.id.imageView)
        val byteArray = (activity as InteractorProvider)
            .getPhotoInteractor()
            .getImages(listOf("imageName"))
            .first()
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        imageView.setImageBitmap(bitmap)
    }

}