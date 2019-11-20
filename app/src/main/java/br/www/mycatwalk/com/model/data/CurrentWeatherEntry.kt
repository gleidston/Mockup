package br.www.mycatwalk.com.model.data


import com.google.gson.annotations.SerializedName
import java.util.concurrent.locks.Condition

data class CurrentWeatherEntry(
        val cloudcover: Int,
        val feelslike: Int,
        val humidity: Int,
        @SerializedName("observation_time")
    val observationTime: String,
        val precip: Int,
        val pressure: Int,
        val temparature: Int,
        @SerializedName("uv_index")
    val uvIndex: Int,
        val visibility: Int,
        @SerializedName("weather_code")
    val weatherCode: Int,
        @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
        @SerializedName("weather_icons")
    val weatherIcons: List<String>,
        @SerializedName("wind_degree")
    val windDegree: Int,
        @SerializedName("wind_dir")
    val windDir: String,
        @SerializedName("wind_speed")
    val windSpeed: Int,
        @SerializedName("is_day")
        val isDay : Int,
        val condition: Condition

        )