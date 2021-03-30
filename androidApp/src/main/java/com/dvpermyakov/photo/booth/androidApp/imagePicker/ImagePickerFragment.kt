package com.dvpermyakov.photo.booth.androidApp.imagePicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvpermyakov.photo.booth.androidApp.InteractorProvider
import com.dvpermyakov.photo.booth.androidApp.databinding.FragmentImagePickerBinding

class ImagePickerFragment : Fragment() {

    private val adapter = ImagePickerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImagePickerBinding.inflate(inflater)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadImages()
        return binding.root
    }

    private fun loadImages() {
        val images = (activity as InteractorProvider)
            .getPhotoInteractor()
            .getImages()
        adapter.items = images
    }

}