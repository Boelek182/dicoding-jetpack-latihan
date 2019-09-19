package com.dicoding.jetpack.latihan.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.ui.academy.adapter.AcademyAdapter
import com.dicoding.jetpack.latihan.ui.academy.viewmodel.AcademyViewModel
import com.dicoding.jetpack.latihan.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return AcademyFragment()
        }
    }

    private var academyViewModel: AcademyViewModel? = null
    private var academyAdapter: AcademyAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            academyViewModel = obtainViewModel(activity)

            academyAdapter = academyViewModel?.getCourses()?.let { AcademyAdapter(activity, it) }
            rvAcademy.layoutManager = LinearLayoutManager(activity)
            rvAcademy.setHasFixedSize(true)
            rvAcademy.adapter = academyAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): AcademyViewModel? {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(AcademyViewModel::class.java) }
    }
}