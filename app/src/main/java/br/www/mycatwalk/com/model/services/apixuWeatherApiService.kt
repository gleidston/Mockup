package br.www.mycatwalk.com.model.services

import br.www.mycatwalk.com.model.data.CurrentWeatherEntry
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

const val API_KEY = "49073ebc1cc10cbe11dbbbdd9b2eda3f"
//http://api.weatherstack.com/current? access_key=49073ebc1cc10cbe11dbbbdd9b2eda3fq=betim&lang=pt

interface apixuWeatherApiService {
// @get("CurrentWeatherEntry.json")           corrigir depois capitulo 2
         fun getCurrent(
         @Query("q")location : String,
         @Query("lang")languageCode:String ="pt"
 ): Deferred<CurrentWeatherEntry>

    companion object{

        operator fun invoke(): apixuWeatherApiService? {
          val requestIterceptor= Interceptor{
              chain ->   val url = chain.request().url().newBuilder().addQueryParameter("key", API_KEY).build()
              val request=chain.request()
                      .newBuilder().url(url)
                      .build()

              return@Interceptor chain.proceed(request)
          }
            val okHttpClient= OkHttpClient.Builder().addInterceptor(requestIterceptor).build()
            return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.weatherstack.com/currentWeatherEntry/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(apixuWeatherApiService::class.java)
        }
    }

}