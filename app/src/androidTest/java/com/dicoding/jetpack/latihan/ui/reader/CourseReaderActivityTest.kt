package com.dicoding.jetpack.latihan.ui.reader

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.utils.FakeDataDummyInstrumentTest
import com.dicoding.jetpack.latihan.utils.RecyclerViewItemCountAssertion
import org.junit.Rule
import org.junit.Test

class CourseReaderActivityTest {

    private val dummyCourse = FakeDataDummyInstrumentTest.generateDummyCourses()[0]

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<CourseReaderActivity> = object : ActivityTestRule<CourseReaderActivity>(CourseReaderActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, CourseReaderActivity::class.java)
            result.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, dummyCourse.courseId)
            return result
        }
    }

    @Test
    fun loadModules() {
        onView(withId(R.id.rvModuleList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).check(RecyclerViewItemCountAssertion(7))
    }

    @Test
    fun clickModule() {
        onView(withId(R.id.rvModuleList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvModuleList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }
}