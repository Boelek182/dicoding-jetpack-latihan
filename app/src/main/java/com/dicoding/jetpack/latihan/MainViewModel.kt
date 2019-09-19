package com.dicoding.jetpack.latihan

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {

    private val ONE_SECOND = 1000L
    private var mInitialTime: Long = 0
    private val mElapsedTime = MutableLiveData<Long>()

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }
}