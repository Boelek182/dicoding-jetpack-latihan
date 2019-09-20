package com.dicoding.jetpack.latihan

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.latihan.adapter.WeatherAdapter
import com.dicoding.jetpack.latihan.data.WeatherItems
import com.dicoding.jetpack.latihan.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: WeatherAdapter? = null
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherAdapter()
        adapter?.notifyDataSetChanged()

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel?.getWeathers()?.observe(this, getWeather)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnCity.setOnClickListener(myListener)
    }

    private val getWeather = Observer<MutableList<WeatherItems>> { weatherItems ->
        if (weatherItems != null) {
            adapter?.setData(weatherItems)
            showLoading(false)
        }
    }

    private var myListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            val city = editCity.text.toString()
            if (TextUtils.isEmpty(city)) return
            mainViewModel?.setWeather(city)
            showLoading(true)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}