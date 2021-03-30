package com.dvpermyakov.photo.booth.androidApp.imagePicker

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dvpermyakov.photo.booth.androidApp.databinding.ItemImageBinding
import com.dvpermyakov.photo.booth.shared.interactors.PhotoInteractor

class ImagePickerAdapter : RecyclerView.Adapter<ImagePickerAdapter.Holder>() {

    var items: List<PhotoInteractor.ImageWithBytes> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemImageBinding.inflate(layoutInflater, parent, false)
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.set(items[position])
    }

    class Holder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun set(image: PhotoInteractor.ImageWithBytes) {
            val bitmap = BitmapFactory.decodeByteArray(image.byteArray, 0, image.byteArray.size)
            binding.imageView.setImageBitmap(bitmap)
        }
    }
}