package com.dvpermyakov.photo.booth.shared.interactors

import com.dvpermyakov.photo.booth.shared.models.Image
import com.dvpermyakov.photo.booth.shared.repository.DBRepository
import com.dvpermyakov.photo.booth.shared.repository.FileRepository

class PhotoInteractor constructor(
    private val fileRepository: FileRepository,
    private val dbRepository: DBRepository
) {

    fun saveImage(name: String, byteArray: ByteArray) {
        dbRepository.createImage(name)
        fileRepository.saveFile(name, byteArray)
    }

    fun getImages(): List<ImageWithBytes> {
        return dbRepository.fetchImages().map { image ->
            ImageWithBytes(
                image = image,
                byteArray = fileRepository.getFile(image.name)
            )
        }
    }

    data class ImageWithBytes(
        val image: Image,
        val byteArray: ByteArray
    )
}