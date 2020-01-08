package com.dicoding.jetpack.latihan.ui.bookmart

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.testing.SingleFragmentActivity
import com.dicoding.jetpack.latihan.utils.EspressoIdlingResource
import com.dicoding.jetpack.latihan.utils.EspressoIdlingResource.getEspressoIdlingResource
import com.dicoding.jetpack.latihan.utils.RecyclerViewItemCountAssertion
import org.junit.After
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
        IdlingRegistry.getInstance().register(getEspressoIdlingResource())
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(getEspressoIdlingResource())
    }

    @Test
    fun loadBookmarks() {
        onView(withId(R.id.rvBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}