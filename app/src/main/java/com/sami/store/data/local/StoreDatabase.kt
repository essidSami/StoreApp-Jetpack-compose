package com.sami.store.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sami.store.data.local.dao.ProductDao
import com.sami.store.domaine.model.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class StoreDatabase: RoomDatabase() {

    abstract val productDao: ProductDao

    companion object{
        const val DATABASE_NAME = "store_db"
    }
}