package com.dicoding.jetpack.latihan.data.source

import androidx.lifecycle.LiveData
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): LiveData<MutableList<CourseEntity>?>

    fun getCourseWithModules(courseId: String?): LiveData<CourseEntity?>

    fun getAllModulesByCourse(courseId: String?): LiveData<MutableList<ModuleEntity>?>

    fun getBookmarkedCourses(): LiveData<MutableList<CourseEntity>?>

    fun getContent(courseId: String?, moduleId: String?): LiveData<ModuleEntity?>
}