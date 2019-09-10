package com.dicoding.jetpack.latihan.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test

class AcademyTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun toDetailActivityTest() {
        onView(withId(R.id.rvAcademy)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAcademy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.textTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.textTitle)).check(matches(withText("Menjadi Android Developer Expert")))
    }

    @Test
    fun toReaderActivityTest() {
        onView(withId(R.id.rvAcademy)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAcademy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btnStart)).check(matches(isDisplayed()))
        onView(withId(R.id.btnStart)).perform(click())
        onView(withId(R.id.frameContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }
}