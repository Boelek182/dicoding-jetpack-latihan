package com.dicoding.jetpack.latihan.ui.academy.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository
import com.dicoding.jetpack.latihan.utils.DataDummy.generateDummyCourses

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): MutableList<CourseEntity> {
        return academyRepository.getAllCourses()
    }
}