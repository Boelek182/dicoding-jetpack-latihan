package com.dicoding.jetpack.latihan.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.ui.academy.AcademyFragment
import com.dicoding.jetpack.latihan.ui.bookmart.BookmarkFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val SELECTED_MENU = "selected_menu"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState != null) {
            savedInstanceState.getInt(SELECTED_MENU)
        } else {
            navView.selectedItemId = R.id.action_home
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        var fragment: Fragment? = null
        when (it.itemId) {
            R.id.action_home -> fragment = AcademyFragment.newInstance()
            R.id.action_bookmark -> fragment = BookmarkFragment.newInstance()
        }

        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit()
        }
        return@OnNavigationItemSelectedListener true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SELECTED_MENU, navView.selectedItemId)
    }
}