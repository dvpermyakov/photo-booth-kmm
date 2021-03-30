package com.dvpermyakov.photo.booth.shared.repository

actual class FileRepository {
    actual fun saveFile(name: String, byteArray: ByteArray) {
    }

    actual fun getFile(name: String): ByteArray {
        return ByteArray(1)
    }
}