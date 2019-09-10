package com.dicoding.jetpack.latihan.ui.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.ui.reader.content.ModuleContentFragment
import com.dicoding.jetpack.latihan.ui.reader.list.ModuleListFragment
import com.dicoding.jetpack.latihan.ui.reader.view.CourseReaderCallback
import com.dicoding.jetpack.latihan.ui.reader.viewmodel.CourseReaderViewModel

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    private var courseReaderViewModel: CourseReaderViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        courseReaderViewModel = ViewModelProviders.of(this).get(CourseReaderViewModel::class.java)

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if (courseId != null) {
                courseReaderViewModel?.courseId = courseId
                populateFragment()
            }
        }
    }

    private fun populateFragment() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if (fragment == null) {
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frameContainer, fragment, ModuleListFragment.TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    override fun moveTo(position: Int, moduleId: String?) {
        val fragment: Fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frameContainer, fragment, ModuleContentFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}