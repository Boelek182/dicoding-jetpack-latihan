package com.dicoding.jetpack.latihan.data.source

import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): List<CourseEntity>?

    fun getCourseWithModules(courseId: String?): CourseEntity?

    fun getAllModulesByCourse(courseId: String?): List<ModuleEntity>?

    fun getBookmarkedCourses(): List<CourseEntity>?

    fun getContent(courseId: String?, moduleId: String?): ModuleEntity?
}