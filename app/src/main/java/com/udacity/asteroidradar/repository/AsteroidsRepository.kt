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
            getTodayMillis(), getEndDateMillis(Period.ONE_WEEK)
        )
    ) {
        it.asDomainModel()
    }

    suspend fun refreshAsteroids(period: Period) {
        withContext(Dispatchers.IO) {
            val jsonResult = Network.nasaApiService.getAsteroids(getToday(), getEndDate(period))
            val asteroids = parseAsteroidsJsonResult(JSONObject(jsonResult))
            database.asteroidDao.insertAll(asteroids.asDatabaseModel())
        }
    }
}