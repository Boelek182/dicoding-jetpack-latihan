package com.dicoding.jetpack.latihan.ui.reader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.data.source.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId: String? = null
    var moduleId: String? = null

    fun getModules(): LiveData<MutableList<ModuleEntity>?> {
        return academyRepository.getAllModulesByCourse(courseId)
    }

    fun getSelectedModule(): LiveData<ModuleEntity?> {
        return academyRepository.getContent(courseId, moduleId)
    }
}