package com.dicoding.jetpack.latihan

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.jetpack.latihan.model.CuboidModel
import com.dicoding.jetpack.latihan.view.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Double.parseDouble


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        btnSave.setOnClickListener(this)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateCircumference.setOnClickListener(this)
        btnCalculateVolume.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val length = edtLength.text.toString().trim { it <= ' ' }
        val width = edtWidth.text.toString().trim { it <= ' ' }
        val height = edtHeight.text.toString().trim { it <= ' ' }
        when {
            TextUtils.isEmpty(length) -> edtLength.error = "Field ini tidak boleh kosong"
            TextUtils.isEmpty(width) -> edtWidth.error = "Field ini tidak boleh kosong"
            TextUtils.isEmpty(height) -> edtHeight.error = "Field ini tidak boleh kosong"
            else -> {
                val l = parseDouble(length)
                val w = parseDouble(width)
                val h = parseDouble(height)
                when (v?.id) {
                    R.id.btnSave -> {
                        mainViewModel?.save(l, w, h)
                        visible()
                    }
                    R.id.btnCalculateCircumference -> {
                        tvResult.text = mainViewModel?.getCircumference().toString()
                        gone()
                    }
                    R.id.btnCalculateSurfaceArea -> {
                        tvResult.text = mainViewModel?.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btnCalculateVolume -> {
                        tvResult.text = mainViewModel?.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        btnCalculateVolume.visibility = View.VISIBLE
        btnCalculateCircumference.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
    }

    private fun gone() {
        btnCalculateVolume.visibility = View.GONE
        btnCalculateCircumference.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnSave.visibility = View.VISIBLE
    }
}