package com.dvpermyakov.photo.booth.shared.repository

expect class FileRepository {
    fun saveFile(name: String, byteArray: ByteArray)
    fun getFiles(names: List<String>): List<ByteArray>
}