package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val asteroidsRepository = AsteroidsRepository(database)

    init {
        viewModelScope.launch {
            try {
                asteroidsRepository.refreshAsteroids()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    val asteroids = asteroidsRepository.asteroids

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid?>()

    val navigateToSelectedAsteroid: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroid

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }
}