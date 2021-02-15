package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.*

fun String.toDateMillis(): Long {
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    val date = dateFormat.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date ?: Date(0)
    return calendar.timeInMillis
}

fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(this)
}

fun getToday(): String {
    val currentTime = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(currentTime)
}

fun getDefaultEndDate(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, Constants.DEFAULT_END_DATE_DAYS)
    val endTime = calendar.time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(endTime)
}

fun getTodayMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.resetTime()
    return calendar.timeInMillis
}

fun getEndDateMillis(numberOfDays: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, numberOfDays)
    calendar.resetTime()
    return calendar.timeInMillis
}

private fun Calendar.resetTime(){
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}