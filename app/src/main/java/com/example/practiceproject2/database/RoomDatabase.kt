package com.example.practiceproject2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practiceproject2.dao.RoomDao
import com.example.practiceproject2.model.RoomEntity

@Database(entities = [RoomEntity::class], version = 1)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun roomDao(): RoomDao
    companion object{
        private var INSTANCE: com.example.practiceproject2.database.RoomDatabase? = null
        fun getDatabase(context: Context): com.example.practiceproject2.database.RoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, com.example.practiceproject2.database.RoomDatabase::class.java,"user_db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}