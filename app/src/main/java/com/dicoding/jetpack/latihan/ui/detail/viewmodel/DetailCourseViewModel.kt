package com.dicoding.jetpack.latihan.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.utils.DataDummy.generateDummyCourses
import com.dicoding.jetpack.latihan.utils.DataDummy.generateDummyModules

class DetailCourseViewModel : ViewModel() {
    private var mCourse: CourseEntity? = null
    var courseId: String? = null

    fun getCourse(): CourseEntity? {
        for (i in generateDummyCourses().indices) {
            val courseEntity = generateDummyCourses()[i]
            if (courseEntity.courseId == courseId) {
                mCourse = courseEntity
            }
        }
        return mCourse
    }

    fun getModules(): MutableList<ModuleEntity> {
        return generateDummyModules(courseId)
    }
}