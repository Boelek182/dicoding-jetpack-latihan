package com.dicoding.jetpack.latihan.ui.bookmart.viewmodel

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class BookmarkViewModelTest {

    private var bookmarkViewModel: BookmarkViewModel? = null

    @Before
    fun setUp() {
        bookmarkViewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmarks() {
        val courseEntities = bookmarkViewModel?.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}