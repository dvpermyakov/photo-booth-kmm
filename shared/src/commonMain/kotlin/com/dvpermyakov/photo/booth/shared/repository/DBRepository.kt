package com.dvpermyakov.photo.booth.shared.repository

import com.dvpermyakov.photo.booth.AppDatabase
import com.dvpermyakov.photo.booth.shared.database.DatabaseDriverFactory
import com.dvpermyakov.photo.booth.shared.models.Image

class DBRepository(
    factory: DatabaseDriverFactory
) {
    private val database = AppDatabase(factory.createDriver())

    fun createImage(name: String) {
        database.imageQueries.insertImage(name)
    }

    fun fetchImages(): List<Image> {
        return database.imageQueries.selectAllImages { id, name -> Image(id, name) }.executeAsList()
    }

}