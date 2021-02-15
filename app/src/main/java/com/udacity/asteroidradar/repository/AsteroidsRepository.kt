package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.asDatabaseModel
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidsRepository(private val database: AsteroidsDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(
        database.asteroidDao.getAsteroids(
            getTodayMillis(), getEndDateMillis(Constants.DEFAULT_END_DATE_DAYS)
        )
    ) {
        it.asDomainModel()
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val jsonResult = Network.nasaApiService.getAsteroids(getToday(), getDefaultEndDate())
            val asteroids = parseAsteroidsJsonResult(JSONObject(jsonResult))
            database.asteroidDao.insertAll(asteroids.asDatabaseModel())
        }
    }
}