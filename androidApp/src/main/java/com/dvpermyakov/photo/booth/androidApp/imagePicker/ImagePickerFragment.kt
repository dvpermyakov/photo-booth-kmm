package com.dvpermyakov.photo.booth.androidApp.imagePicker

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvpermyakov.photo.booth.androidApp.InteractorProvider
import com.dvpermyakov.photo.booth.androidApp.databinding.FragmentImagePickerBinding
import com.dvpermyakov.photo.booth.shared.interactors.PhotoInteractor

class ImagePickerFragment : Fragment() {

    private val adapter = ImagePickerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImagePickerBinding.inflate(inflater)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.clickListener = { image ->
            setWallpaper(image)
        }
        loadImages()
        return binding.root
    }

    private fun setWallpaper(image: PhotoInteractor.ImageWithBytes) {
        val bitmap = BitmapFactory.decodeByteArray(image.byteArray, 0, image.byteArray.size)
        WallpaperManager
            .getInstance(requireContext())
            .setBitmap(bitmap)
    }

    private fun loadImages() {
        val images = (activity as InteractorProvider)
            .getPhotoInteractor()
            .getImages()
        adapter.items = images
    }

}