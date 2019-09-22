package com.dicoding.jetpack.latihan

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack.latihan.EspressoIdlingResource.getEspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(getEspressoIdlingResource())
    }

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkText() {
        onView(withId(R.id.textView)).check(matches(withText(mActivityRule.activity.getString(R.string.prepare))))

        onView(withText(mActivityRule.activity.getString(R.string.start))).perform(click())

        onView(withId(R.id.textView)).check(matches(withText(mActivityRule.activity.getString(R.string.delay2))))
    }
}