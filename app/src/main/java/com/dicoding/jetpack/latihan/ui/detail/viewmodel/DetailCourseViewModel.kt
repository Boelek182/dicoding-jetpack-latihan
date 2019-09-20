package com.dicoding.jetpack.latihan.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    var courseId: String? = null

    fun getCourse(): LiveData<CourseEntity?> {
        return academyRepository.getCourseWithModules(courseId)
    }

    fun getModules(): LiveData<MutableList<ModuleEntity>?> {
        return academyRepository.getAllModulesByCourse(courseId)
    }
}