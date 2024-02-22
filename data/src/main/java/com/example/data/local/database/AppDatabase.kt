package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.Dao.CartDao
import com.example.domain.entities.ProductCartEntitity

@Database(entities = [ProductCartEntitity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun cartDao():CartDao
}