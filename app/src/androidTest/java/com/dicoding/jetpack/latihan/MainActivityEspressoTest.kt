package com.dicoding.jetpack.latihan

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityEspressoTest {
    //val dummyVolume = "504.0"
    private val dummyCircumference = "2016.0"
    //val dummySurfaceArea = "396.0"
    private val dummyLength = "12.0"
    private val dummyWidth = "7.0"
    private val dummyHeight = "6.0"
    //val emptyInput = ""
    //val fieldEmpty = "Field ini tidak boleh kosong"

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun getCircumference() {
        onView(withId(R.id.edtLength)).perform(
            typeText(dummyLength),
            closeSoftKeyboard()
        )
        onView(withId(R.id.edtWidth)).perform(
            typeText(dummyWidth),
            closeSoftKeyboard()
        )
        onView(withId(R.id.edtHeight)).perform(
            typeText(dummyHeight),
            closeSoftKeyboard()
        )
        onView(withId(R.id.btnSave))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())
        onView(withId(R.id.btnCalculateCircumference))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btnCalculateCircumference))
            .perform(click())
        onView(withId(R.id.tvResult))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvResult))
            .check(matches(withText(dummyCircumference)))
    }
}