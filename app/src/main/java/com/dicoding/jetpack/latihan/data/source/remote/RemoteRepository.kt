package com.dicoding.jetpack.latihan.data.source.remote

import com.dicoding.jetpack.latihan.data.source.remote.response.ContentResponse
import com.dicoding.jetpack.latihan.data.source.remote.response.CourseResponse
import com.dicoding.jetpack.latihan.data.source.remote.response.ModuleResponse
import com.dicoding.jetpack.latihan.utils.JsonHelper

class RemoteRepository(private val jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getAllCourses(): MutableList<CourseResponse> {
        return jsonHelper.loadCourses()
    }

    fun getModules(courseId: String?): MutableList<ModuleResponse> {
        return jsonHelper.loadModule(courseId)
    }

    fun getContent(moduleId: String?): ContentResponse? {
        return jsonHelper.loadContent(moduleId)
    }
}