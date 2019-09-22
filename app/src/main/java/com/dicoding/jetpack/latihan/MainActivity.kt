package com.dicoding.jetpack.latihan

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.jetpack.latihan.EspressoIdlingResource.decrement
import com.dicoding.jetpack.latihan.EspressoIdlingResource.getEspressoIdlingResource
import com.dicoding.jetpack.latihan.EspressoIdlingResource.increment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            delay1()
            delay2()
        }
    }

    private fun delay1() {
        increment()
        Handler().postDelayed({
            textView.text = getString(R.string.delay1)

            if (!getEspressoIdlingResource().isIdleNow) {
                decrement()
            }
        }, 2000L)
    }

    private fun delay2() {
        increment()
        Handler().postDelayed({
            textView.text = getString(R.string.delay2)

            if (!getEspressoIdlingResource().isIdleNow) {
                decrement()
            }
        }, 3000L)
    }
}