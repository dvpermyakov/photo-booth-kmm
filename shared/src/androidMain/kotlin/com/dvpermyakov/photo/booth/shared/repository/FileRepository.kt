package com.dvpermyakov.photo.booth.shared.repository

import android.content.Context

actual class FileRepository(
    private val context: Context
) {
    actual fun saveFile(name: String, byteArray: ByteArray) {
        context.openFileOutput(name, Context.MODE_PRIVATE).use { stream ->
            stream.write(byteArray)
        }
    }

    actual fun getFile(name: String): ByteArray {
        return context.openFileInput(name).use { stream ->
            stream.readBytes()
        }
    }

}