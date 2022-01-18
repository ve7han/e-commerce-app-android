package com.example.a3e_commerce.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductDatabase")
    fun getAll(): List<ProductDatabase>

    @Query("SELECT * FROM ProductDatabase WHERE title LIKE :term")
    fun searchFor(term: String): List<ProductDatabase>

    @Insert
    fun insertAll(vararg products: ProductDatabase)
}
