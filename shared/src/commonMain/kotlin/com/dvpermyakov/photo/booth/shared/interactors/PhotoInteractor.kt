package com.dvpermyakov.photo.booth.shared.interactors

import com.dvpermyakov.photo.booth.shared.repository.FileRepository

class PhotoInteractor constructor(
    private val fileRepository: FileRepository
) {

    fun saveImage(name: String, byteArray: ByteArray) {
        fileRepository.saveFile(name, byteArray)
    }

    fun getImages(names: List<String>): List<ByteArray> {
        return fileRepository.getFiles(names)
    }
}