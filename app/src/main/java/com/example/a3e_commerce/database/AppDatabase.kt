package com.example.a3e_commerce.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ProductDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
