package com.dicoding.jetpack.latihan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mLiveDataTimerViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long> {
            val newText = this@MainActivity.resources.getString(R.string.seconds, it)
            timer_textview.text = newText
        }
        mLiveDataTimerViewModel?.getElapsedTime()?.observe(this, elapsedTimeObserver)
    }
}