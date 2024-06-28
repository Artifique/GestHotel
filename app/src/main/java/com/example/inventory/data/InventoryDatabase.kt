package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class,history::class], version = 7, exportSchema = false)
abstract class InventoryDatabase:RoomDatabase() {
    abstract fun itemDao(): itemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this)
            {Room.databaseBuilder(context,InventoryDatabase::class.java,"HOTEL_database").
            fallbackToDestructiveMigration().build().also { Instance= it } }
        }
    }
}