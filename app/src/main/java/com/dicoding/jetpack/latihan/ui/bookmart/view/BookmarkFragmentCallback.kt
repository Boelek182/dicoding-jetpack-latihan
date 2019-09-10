package com.dicoding.jetpack.latihan.ui.bookmart.view

import com.dicoding.jetpack.latihan.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity?)
}