package com.example.android.sunshine.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = arrayOf(WeatherEntry::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class SunshineDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao  //Getters for Dao

    companion object {

        private val DATABASE_NAME = "weather"

        // For Singleton instantiation
        private val LOCK = Any()
        @Volatile
        private var sInstance: SunshineDatabase? = null

        fun getInstance(context: Context): SunshineDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(context.applicationContext,
                                SunshineDatabase::class.java, SunshineDatabase.DATABASE_NAME).build()
                    }
                }
            }
            return sInstance
        }
    }
}