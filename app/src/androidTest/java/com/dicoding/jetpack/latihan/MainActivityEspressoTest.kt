package com.dicoding.jetpack.latihan

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {
    val dummyVolume = "504.0"
    val dummyCircumference = "2016.0"
    val dummySurfaceArea = "396.0"
    val dummyLength = "12.0"
    val dummyWidth = "7.0"
    val dummyHeight = "6.0"
    val emptyInput = ""
    val fieldEmpty = "Field ini tidak boleh kosong"

    @Rule
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