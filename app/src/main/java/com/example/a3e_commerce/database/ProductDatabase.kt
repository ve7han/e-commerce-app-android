package com.example.a3e_commerce.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDatabase (
    @PrimaryKey(autoGenerate = true) val uid: Int?,

    @ColumnInfo val title: String,

    @ColumnInfo val price: Double,

        )

