package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.Constants.IMAGE_MEDIA_TYPE
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

enum class AsteroidsFilter { SHOW_WEEK, SHOW_TODAY, SHOW_SAVED }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val asteroidsRepository = AsteroidsRepository(database)

    init {
        viewModelScope.launch {
            try {
                asteroidsRepository.refreshAsteroids(Period.ONE_WEEK)
                asteroidsRepository.refreshPictureOfDay()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private var _asteroids = asteroidsRepository.weekAsteroids

    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    val pictureOfDay = asteroidsRepository.pictureOfDay

    val pictureOfDayDescription = Transformations.map(pictureOfDay) {
        if (it.mediaType == IMAGE_MEDIA_TYPE)
            application.applicationContext.getString(
                R.string.nasa_picture_of_day_content_description_format,
                it.title
            )
        else
            application.applicationContext.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)

    }

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid?>()

    val navigateToSelectedAsteroid: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroid

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    fun filterAsteroids(filter: AsteroidsFilter) {
        _asteroids = when (filter) {
            AsteroidsFilter.SHOW_SAVED -> asteroidsRepository.savedAsteroids
            AsteroidsFilter.SHOW_TODAY -> asteroidsRepository.todayAsteroids
            AsteroidsFilter.SHOW_WEEK -> asteroidsRepository.weekAsteroids
        }
    }
}