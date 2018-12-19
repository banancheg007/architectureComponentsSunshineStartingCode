package com.example.android.sunshine.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import java.util.Date

/**
 * [Dao] which provides an api for all data operations
 */
@Dao
interface WeatherDao {
    /**
     * Gets the weather for a single day
     *
     * @param date The date you want weather for
     * @return Weather for a single day
     */
    @Query("SELECT * FROM weather WHERE date = :date")
    fun getWeatherByDate(date: Date): WeatherEntry

    /**
     * Inserts a list of [WeatherEntry] into the weather table. If there is a conflicting id
     * or date the weather entry uses the [OnConflictStrategy] of replacing the weather
     * forecast. The required uniqueness of these values is defined in the [WeatherEntry].
     *
     * @param weather A list of weather forecasts to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg weather: WeatherEntry)

}