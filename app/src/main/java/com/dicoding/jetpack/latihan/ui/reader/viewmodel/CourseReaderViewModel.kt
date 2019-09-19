package com.dicoding.jetpack.latihan.ui.reader.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId: String? = null
    var moduleId: String? = null

    fun getModules(): MutableList<ModuleEntity> {
        return academyRepository.getAllModulesByCourse(courseId)
    }

    fun getSelectedModule(): ModuleEntity? {
        return academyRepository.getContent(courseId, moduleId)
    }
}