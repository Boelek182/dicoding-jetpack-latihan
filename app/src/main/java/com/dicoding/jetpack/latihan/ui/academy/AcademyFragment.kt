package com.dicoding.jetpack.latihan.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.ui.academy.adapter.AcademyAdapter
import com.dicoding.jetpack.latihan.utils.DataDummy.generateDummyCourses
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return AcademyFragment()
        }
    }

    private var academyAdapter: AcademyAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            academyAdapter = AcademyAdapter(activity, generateDummyCourses())
            rvAcademy.layoutManager = LinearLayoutManager(activity)
            rvAcademy.setHasFixedSize(true)
            rvAcademy.adapter = academyAdapter
        }
    }
}