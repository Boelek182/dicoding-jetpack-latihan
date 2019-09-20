package com.dicoding.jetpack.latihan.data.source.remote

import android.os.Handler
import com.dicoding.jetpack.latihan.data.source.remote.view.GetContentCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadCoursesCallback
import com.dicoding.jetpack.latihan.data.source.remote.view.LoadModulesCallback
import com.dicoding.jetpack.latihan.utils.JsonHelper

class RemoteRepository(private val jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null
        private const val SERVICE_LATENCY_IN_MILLIS = 2000L

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String?, callback: LoadModulesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllModulesReceived(jsonHelper.loadModule(courseId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String?, callback: GetContentCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onContentReceived(jsonHelper.loadContent(moduleId)) }, SERVICE_LATENCY_IN_MILLIS)
    }
}