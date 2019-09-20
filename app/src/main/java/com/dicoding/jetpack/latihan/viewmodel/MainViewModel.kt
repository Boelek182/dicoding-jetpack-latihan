package com.dicoding.jetpack.latihan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.WeatherItems
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {
    private val apiKey = "4851f54f70c10c4270dce2ee604dfaf4"
    private val listWeathers = MutableLiveData<MutableList<WeatherItems>>()

    fun setWeather(cities: String?) {
        val client = AsyncHttpClient()
        val listItems = ArrayList<WeatherItems>()
        val url = "https://api.openweathermap.org/data/2.5/group?id=" + cities.toString() + "&units=metric&appid=" + apiKey
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                try {
                    val result = responseBody?.let { String(it) }
                    val responseObject = JSONObject(result.toString())
                    val list = responseObject.getJSONArray("list")
                    for (i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems(weather)
                        listItems.add(weatherItems)
                    }
                    listWeathers.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("onFailure", error?.message.toString())
            }
        })
    }

    fun getWeathers(): LiveData<MutableList<WeatherItems>> {
        return listWeathers
    }
}