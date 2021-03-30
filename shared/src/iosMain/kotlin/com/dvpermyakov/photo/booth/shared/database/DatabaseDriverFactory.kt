package com.dvpermyakov.photo.booth.shared.database

import com.dvpermyakov.photo.booth.AppDatabase
import com.squareup.sqldelight.db.SqlDriver


actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "test.db")
    }
}