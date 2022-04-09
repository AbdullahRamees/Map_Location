package com.atarious.map_location.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atarious.map_location.Data.dao.CityDao
import com.atarious.map_location.Data.tables.City

@Database(entities = [City::class],version = 1,exportSchema = false)
abstract class LocalDatabase:RoomDatabase() {

    abstract  fun cityDao():CityDao

    companion object{
        @Volatile
        private  var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context):LocalDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "Local_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}