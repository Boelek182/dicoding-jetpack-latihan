package com.dicoding.jetpack.latihan.ui.bookmart

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.testing.SingleFragmentActivity
import com.dicoding.jetpack.latihan.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookmarkFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val bookmarkFragment = BookmarkFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @Test
    fun loadBookmarks() {
        try {
            Thread.sleep(3000L)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rvBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}