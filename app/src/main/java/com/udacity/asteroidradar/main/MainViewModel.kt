package com.udacity.asteroidradar.main

import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

    val placeholderAsteroids =
        listOf(
            Asteroid(
                1234567,
                "68347 (2002 KB67)",
                "2020-02-08",
                0.0,
                0.0,
                0.0,
                0.0,
                true
            ), Asteroid(
                1234567,
                "68347 (2003 KB67)",
                "2020-02-08",
                0.0,
                0.0,
                0.0,
                0.0,
                false
            ), Asteroid(
                1234567,
                "68347 (2004 KB67)",
                "2020-02-08",
                0.0,
                0.0,
                0.0,
                0.0,
                true
            ), Asteroid(
                1234567,
                "68347 (2021 KB67)",
                "2020-02-08",
                0.0,
                0.0,
                0.0,
                0.0,
                false
            ), Asteroid(
                1234567,
                "68347 (2001 KB67)",
                "2020-02-08",
                0.0,
                0.0,
                0.0,
                0.0,
                true
            )
        )
}