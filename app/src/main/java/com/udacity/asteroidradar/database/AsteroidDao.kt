package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM database_asteroid WHERE closeApproachDateMillis BETWEEN :startDateMillis AND :endDateMillis ORDER BY closeApproachDateMillis")
    fun getAsteroids(startDateMillis: Long, endDateMillis: Long): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM database_asteroid ORDER BY closeApproachDateMillis")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<DatabaseAsteroid>)
}