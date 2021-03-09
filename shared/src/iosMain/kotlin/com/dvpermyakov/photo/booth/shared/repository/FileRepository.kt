package com.dvpermyakov.photo.booth.shared.repository

actual class FileRepository {
    actual fun saveFile(name: String, byteArray: ByteArray) {
    }

    actual fun getFiles(names: List<String>): List<ByteArray> {
        return emptyList()
    }
}