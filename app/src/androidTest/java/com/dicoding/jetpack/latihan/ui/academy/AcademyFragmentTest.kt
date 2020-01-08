package com.dicoding.jetpack.latihan.ui.academy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.testing.SingleFragmentActivity
import com.dicoding.jetpack.latihan.utils.EspressoIdlingResource.getEspressoIdlingResource
import com.dicoding.jetpack.latihan.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AcademyFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val academyFragment = AcademyFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(getEspressoIdlingResource())
        activityRule.activity.setFragment(academyFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(getEspressoIdlingResource())
    }

    @Test
    fun loadCourse() {
        onView(withId(R.id.rvAcademy)).check(matches(isDisplayed()))
        onView(withId(R.id.rvAcademy)).check(RecyclerViewItemCountAssertion(5))
    }
}