package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.toDateMillis
import java.text.SimpleDateFormat
import java.util.*

data class NetworkAsteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

fun ArrayList<NetworkAsteroid>.asDatabaseModel(): List<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDateMillis = it.closeApproachDate.toDateMillis(),
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
        )
    }
}