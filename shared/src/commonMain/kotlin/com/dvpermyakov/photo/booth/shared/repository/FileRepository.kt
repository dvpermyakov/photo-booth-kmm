package com.dvpermyakov.photo.booth.shared.repository

expect class FileRepository {
    fun saveFile(name: String, byteArray: ByteArray)
    fun getFile(name: String): ByteArray
}