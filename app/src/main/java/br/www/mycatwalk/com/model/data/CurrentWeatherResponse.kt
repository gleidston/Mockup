package br.www.mycatwalk.com.model.data

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
        @SerializedName("current")
        val currentWeatherEntry: CurrentWeatherEntry,
        val location: Location,
        val request: Request
)