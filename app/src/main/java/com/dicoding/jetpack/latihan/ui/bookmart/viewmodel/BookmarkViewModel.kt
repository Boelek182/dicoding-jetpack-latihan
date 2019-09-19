package com.dicoding.jetpack.latihan.ui.bookmart.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): MutableList<CourseEntity> {
        return academyRepository.getBookmarkedCourses()
    }
}