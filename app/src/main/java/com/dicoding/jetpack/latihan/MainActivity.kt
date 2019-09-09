package com.dicoding.jetpack.latihan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.dicoding.jetpack.latihan.view.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        displayResult()

        btnCalculate.setOnClickListener {
            val width = edtWidth.text.toString()
            val height = edtHeight.text.toString()
            val length = edtLength.text.toString()
            when {
                width.isEmpty() -> edtWidth.error = "Masih kosong"
                height.isEmpty() -> edtHeight.error = "Masih kosong"
                length.isEmpty() -> edtLength.error = "Masih kosong"
                else -> {
                    viewModel?.calculate(width, height, length)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult() {
        tvResult.text = viewModel?.result.toString()
    }
}