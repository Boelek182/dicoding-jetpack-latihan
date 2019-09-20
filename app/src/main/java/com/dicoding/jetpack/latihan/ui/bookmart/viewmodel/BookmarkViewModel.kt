package com.dicoding.jetpack.latihan.ui.bookmart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<MutableList<CourseEntity>?> {
        return academyRepository.getBookmarkedCourses()
    }
}