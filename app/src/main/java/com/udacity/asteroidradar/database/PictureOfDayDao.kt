package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PictureOfDayDao {

    @Query("SELECT * FROM database_picture_of_day ORDER BY id DESC LIMIT 1")
    fun getPictureOfDay(): LiveData<DatabasePictureOfDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pictureOfDay: DatabasePictureOfDay)
}